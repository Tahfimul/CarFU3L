import simplejson as json
from firebase import firebase

# Script for reading exported json from db to extract make of models of each vehicle type for MakeModels category
# ---TYPE Cars/Motorcycles
# -----CATEGORIES FuelEcon/MAKE/MODEL -- MakeModels
# -------Make
# --------Model - Model

firebase = firebase.FirebaseApplication('https://r-pi-5f7e8.firebaseio.com/', None)

def unpackModel(type, model, child):
    make = child["Make"]
    result = firebase.put(type+'/MakeModels/'+make+"/", name=str(model),data=str(model))
    print(model, child["Make"])

def unpackModels(type, models):
    for model in models.keys():
        unpackModel(type, model, models[str(model)])

def unpackCategories(type, categories):
    unpackModels(type, categories["Models"])

with open('r-pi-5f7e8-export.json') as f:

    config = json.load(f)

    for type, val in config.items():
            categories = val
            unpackCategories(type, categories)
            f.close()
