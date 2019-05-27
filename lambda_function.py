import logging
import boto3
import json

logger = logging.getLogger()
logger.setLevel(logging.DEBUG)

iotDataClient = boto3.client('iot-data')
iotClient = boto3.client('iot')

def lambda_handler(event, context):
    logger.info('got event{}'.format(event))
    if (event['directive']['header']['namespace'] == 'Alexa.Discovery' and
        event['directive']['header']['name'] == 'Discover'):
        return handleDiscovery(context, event)
    elif event['directive']['header']['namespace'] == 'Alexa.PowerController':
        return handlePowerControl(context, event)
    #elif event['directive']['header']['namespace'] == 'Alexa.PowerLevelController':
    #    return handlePowerLevelControl(context, event)
    #elif (event['directive']['header']['namespace'] == 'Alexa' and 
    #    event['directive']['header']['name'] == 'ReportState'):
    #    return handleState(context, event)

def handleDiscovery(context, event):
  #get the things from SmartyPi4home group 
  logger.info('got discovery request {}'.format(event))
  iotResponse = iotClient.list_things_in_thing_group(
    thingGroupName='SmartyPi4Home-Things',
    recursive=False,
    maxResults=25
  )
  
  logger.debug('available iot devices {}'.format(iotResponse))
  endpoints = []
  for thing in iotResponse['things']:
    thingDescription = iotClient.describe_thing(thingName=thing)
    logger.debug('processing thing {}'.format(thingDescription))
    
    if thingDescription['thingTypeName'] == 'SmartyPi4Home-Outlet':
      logger.debug('found smart outlet')
      endpoints.append({
        'endpointId': thing,
        'friendlyName': thingDescription['attributes']['friendlyName'].replace('-',' '),
        'description': thingDescription['attributes']['friendlyName'].replace('-',' ') + ' controlled by ' + thing,
        'manufacturerName': 'SmartyPi4Home',
        "displayCategories": [
             "LIGHT"
        ],
        'capabilities': [
           {
              'type': 'AlexaInterface',
              'interface': 'Alexa.PowerController',
              'version': '3',
              'properties': {
                'supported': [
                  {
                    'name': 'powerState'
                  }
                ],
                'proactivelyReported': 'false',
                'retrievable': 'false'
              }
            }  
        ]
      })
    elif thingDescription['thingTypeName'] == 'SmartyPi4Home-Fan':
      logger.debug('found smart fan')
    else:
      logger.error('Unable to handle thing type {}'.format(thingDescription['thingTypeName']))
  
  
  return {
    'event': {
      'header': {
          'messageId': event['directive']['header']['messageId'],
          'name': 'Discover.Response',
          'namespace': 'Alexa.Discovery',
          'payloadVersion': '3'
      },
      'payload': {
          'endpoints': endpoints
      }
    }
  }

def handlePowerControl(context, event):
    device_id = event['directive']['endpoint']['endpointId']
    requestType = event['directive']['header']['name']
    if requestType == 'TurnOn':
        status = 'ON'
    elif requestType == 'TurnOff':
        status = 'OFF'

    response = iotDataClient.update_thing_shadow(
        thingName=device_id,
        payload=json.dumps({
            'state': {
                'desired': {
                    'status': status
                }
            }
        })
    )

    logger.info('received {}'.format(response))

    return {
        'context':{
            'properties':[
                {
                    'namespace': 'Alexa.PowerController',
                    'name': 'powerState',
                    'value': status
                }
            ]
        },
        'event': {
            'header': {
                'messageId': event['directive']['header']['messageId'],
                'name': 'Response',
                'namespace':'Alexa',
                'payloadVersion':'3'
            },
            'endpoint': {
                'endpointId': device_id
            },
            'payload': {}
        }
    }
    
def handleState(context, event):
  logger.info('handling status request')
  
  endpointId = event['directive']['endpoint']['endpointId']
  
  response = iotDataClient.get_thing_shadow(
    thingName = endpointId
  )
  
  deviceShadow = json.loads(response['payload'].read())
  
  logger.info('current shadow {}'.format(deviceShadow))
  
  return {
     'context':{  
        'properties':[  
           {  
              'namespace':'Alexa.PowerController',
              'name':'powerState',
              'value':deviceShadow['state']['reported']['status']
           }
        ]
     },
     'event':{  
        'header':{  
           'messageId':event['directive']['header']['messageId'],
           'namespace':'Alexa',
           'name':'StateReport',
           'payloadVersion':'3'
        },
        'endpoint':{  
           'endpointId':endpointId
        },
        'payload':{}
     }
  }
