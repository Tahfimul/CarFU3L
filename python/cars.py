import requests
from bs4 import BeautifulSoup
import html.parser
from firebase import firebase
import time
import firebase_admin
from firebase_admin import db
from firebase_admin import credentials

# Main script to scrape site and post data for each vehicle type

cred = credentials.Certificate("r-pi-5f7e8-firebase-adminsdk-0u7u5-14bc9447c7.json")
# firebase_admin.initialize_app(cred)

app = firebase_admin.initialize_app(cred, {'databaseURL' : 'https://r-pi-5f7e8.firebaseio.com/'})
# ref = db.reference()

firebase = firebase.FirebaseApplication('https://r-pi-5f7e8.firebaseio.com/', None)

# res = requests.get('http://www.fuelly.com/car')
#
# soup = BeautifulSoup(res.text, 'html.parser')

CarMake = []
CarModel = []
CarFuelEcon = []
MotorcycleMake = []
MotorcycleModel = []
MotorcycleFuelEcon = []
HeavyVehicleMake = []
HeavyVehicleModel = []
HeavyVehicleFuelEcon = []
Atv_UtvMake = []
AtvModel = []
AtvFuelEcon = []

i=0

def incrementCounter():
    global i
    i+=1

def resetCounter():
    global i
    i = 0

def appendCarMake(m):
    global CarMake
    print("Appended car make "+m)
    CarMake.append(m)

def appendCarModel(mo):
    global CarModel
    print("Appened car model "+mo)
    CarModel.append(mo)

def appendCarFuelEcon(mo):
    global CarFuelEcon
    print("Appended car fuel econ "+mo)
    CarFuelEcon.append(mo)

def appendMotorcycleMake(m):
    global MotorcycleMake
    print("Appended motorcycle make "+m)
    MotorcycleMake.append(m)

def appendMotorcycleModel(mo):
    global MotorcycleModel
    print("Appended motorcycle model "+mo)
    MotorcycleModel.append(mo)

def appendMotorcycleFuelEcon(mo):
    global MotorcycleFuelEcon
    print("Appended motorcycle fuel econ "+mo)
    MotorcycleFuelEcon.append(mo)

def appendHeavyVehiclesMake(m):
    global HeavyVehicleMake
    print("Appended Truck make "+m)
    HeavyVehicleMake.append(m)

def appendHeavyVehicleModel(mo):
    global HeavyVehicleModel
    print("Appended Truck model "+mo)
    HeavyVehicleModel.append(mo)

def appendHeavyVehicleFuelEcon(mo):
    global HeavyVehicleFuelEcon
    print("Appended truck fuel econ "+mo)
    HeavyVehicleFuelEcon.append(mo)

def appendAtvsUtvsMake(m):
    global Atvs_UtvsMake
    print("Appended atv make "+m)
    Atv_UtvMake.append(m)

def appendAtvModel(mo):
    global AtvModel
    print("Appended atv model "+mo)
    AtvModel.append(mo)


def appendAtvFuelEcon(mo):
    global AtvFuelEcon
    print("Appended atv fuel econ "+mo)
    AtvFuelEcon.append(mo)

def CarFuelEconHasMo(mo):
    if mo in CarFuelEcon:
        return 1
    else:
        return -1

def CarModelHasMo(mo):
    if mo in CarModel:
        return 1
    else:
        return -1

def CarMakeHasM(m):
    if m in CarMake:
        return 1
    else:
        return -1

def MotorcycleFuelEconHasMo(mo):
    if mo in MotorcycleFuelEcon:
        return 1
    else:
        return -1

def MotorcycleModelHasMo(mo):
    if mo in MotorcycleModel:
        return 1
    else:
        return -1

def MotorcycleMakeHasM(m):
    if m in MotorcycleMake:
        return 1
    else:
        return -1

def HeavyVehicleFuelEconHasMo(mo):
    if mo in HeavyVehicleFuelEcon:
        return 1
    else:
        return -1

def HeavyVehicleModelHasMo(mo):
    if mo in HeavyVehicleModel:
        return 1
    else:
        return -1

def HeavyVehicleMakeHasM(m):
    if m in HeavyVehicleMake:
        return 1
    else:
        return -1

def AtvFuelEconHasMo(mo):
    if mo in AtvFuelEcon:
        return 1
    else:
        return -1

def AtvModelHasMo(mo):
    if mo in AtvModel:
        return 1
    else:
        return -1

def AtvMakeHasM(m):
    if m in Atv_UtvMake:
        return 1
    else:
        return -1

def createDummyEntriesFuelEconDB():
    result = firebase.put('/Cars/FuelEcon/'+"MODEL"+"/"+"0000", name="Make",data="MAKE")
    print("Created Cars FuelEcon Dummy Entry DB")
    result = firebase.put('/Motorcycles/FuelEcon/'+"MODEL"+"/"+"0000", name="Make",data="MAKE")
    print("Created Motorcycles FuelEcon Dummy Entry DB")
    result = firebase.put('/HeavyVehicles/FuelEcon/'+"MODEL"+"/"+"0000", name="Make",data="MAKE")
    print("Created HeavyVehicles FuelEcon Dummy Entry DB")
    result = firebase.put('/Atvs/FuelEcon/'+"MODEL"+"/"+"0000", name="Make",data="MAKE")
    print("Created Atvs FuelEcon Dummy Entry DB")

def checkCarFuelEconDB():
    ref = db.reference('/Cars/FuelEcon')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendCarFuelEcon(key)

def checkMotorcycleFuelEconDB():
    ref = db.reference('/Motorcycles/FuelEcon')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendMotorcycleFuelEcon(key)

def checkHeavyVehicleFuelEconDB():
    ref = db.reference('/HeavyVehicles/FuelEcon')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendHeavyVehicleFuelEcon(key)

def checkAtvFuelEconDB():
    ref = db.reference('/Atvs/FuelEcon')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendAtvFuelEcon(key)

def checkAllFuelEconDB():
    checkCarFuelEconDB()
    checkMotorcycleFuelEconDB()
    checkHeavyVehicleFuelEconDB()
    checkAtvFuelEconDB()

def checkCarModelDB():
    ref = db.reference('/Cars/Models')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendCarModel(key)

def checkMotorcycleModelDB():
    ref = db.reference('/Motorcycles/Models')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendMotorcycleModel(key)

def checkAtvModelDB():
    ref = db.reference('/Atvs/Models')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendAtvModel(key)

def checkHeavyVehicleModelDB():
    ref = db.reference('/HeavyVehicles/Models')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendHeavyVehicleModel(key)

def checkAllModelDB():
    checkCarModelDB()
    checkMotorcycleModelDB()
    checkAtvModelDB()
    checkHeavyVehicleModelDB()

def checkCarMakeDB():
    ref = db.reference('/Cars/Makes')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendCarMake(key)

def checkMotorcycleMakeDB():
    ref = db.reference('/Motorcycles/Makes')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendMotorcycleMake(key)

def checkHeavyVehicleMakeDB():
    ref = db.reference('/HeavyVehicles/Makes')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendHeavyVehiclesMake(key)

def checkAtvMakeDB():
    ref = db.reference('/Atvs/Makes')
    snapshot = ref.order_by_key().get()
    for key, val in snapshot.items():
        appendAtvsUtvsMake(key)

def checkAllMakeDB():
    checkCarMakeDB()
    checkMotorcycleMakeDB()
    checkHeavyVehicleMakeDB()
    checkAtvMakeDB()

def postCarFuelEcon(mk, yr, mo, fI):
    result = firebase.put('/Cars/FuelEcon/'+str(mo)+"/"+str(yr), name="Make",data=mk)
    result = firebase.put('/Cars/FuelEcon/'+str(mo)+"/"+str(yr), name="Model",data=mo)
    result = firebase.put('/Cars/FuelEcon/'+str(mo)+"/"+str(yr), name="FuelEcon",data=fI)
    print(yr+" "+mo+" "+mk+" Car FuelEcon Data Posted ")

def getCarFuelEconByYears(mk, mo):
    MK = mk
    MO = mo
    mk = mk.replace(" ","_")
    mk = mk.lower()
    mo = mo.replace(" ","_")
    mo = mo.lower()
    print("getting car fuel econ by years "+mk+" "+mo)
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/car/'+mk+'/'+mo)
    soup = BeautifulSoup(res.text, 'html.parser')
    postCarFuelEcon(MK, "Yr", MO, "fI")
    for years in soup.find_all("ul", "model-year-summary"):
        year = years.find("li", "summary-year").get_text()
        fuelInfo = years.find("li", "summary-avg").get_text()
        print("posting car fuel econ "+fuelInfo+" "+year+" "+MK+" "+MO)
        postCarFuelEcon(mk, year, mo, fuelInfo)


def postMotorcycleFuelEcon(mk, yr, mo, fI):
    result = firebase.put('/Motorcycles/FuelEcon/'+mo+"/"+yr, name="Make",data=mk)
    result = firebase.put('/Motorcycles/FuelEcon/'+mo+"/"+yr, name="Model",data=mo)
    result = firebase.put('/Motorcycles/FuelEcon/'+mo+"/"+yr, name="FuelEcon",data=fI)
    print(yr+" "+mo+" "+mk+" Motorcycle FuelEcon Data Posted")

def getMotorcycleFuelEconByYears(mk, mo):
    MK = mk
    MO = mo
    print("getting fuel econ for motorcycle "+mk+" "+mo)
    mk = mk.replace(" ","_")
    mk = mk.lower()
    mo = mo.replace(" ","_")
    mo = mo.lower()
    res = requests.get('https://web.archive.org/web/20190623094600/http://www.fuelly.com/motorcycle/'+mk+'/'+mo)
    postMotorcycleFuelEcon(MK, "Yr", MO, "fI")
    soup = BeautifulSoup(res.text, 'html.parser')
    for years in soup.find_all("ul", "model-year-summary"):
        year = years.find("li", "summary-year").get_text()
        print("YEar", str(year))
        fuelInfo = years.find("li", "summary-avg").get_text()
        postMotorcycleFuelEcon(MK, year, MO, fuelInfo)

def postHeavyVehicleFuelEcon(mk, yr, mo, fI):
    result = firebase.put('/HeavyVehicles/FuelEcon/'+mo+"/"+yr, name="Make",data=mk)
    result = firebase.put('/HeavyVehicles/FuelEcon/'+mo+"/"+yr, name="Model",data=mo)
    result = firebase.put('/HeavyVehicles/FuelEcon/'+mo+"/"+yr, name="FuelEcon",data=fI)
    print(yr+" "+mo+" "+mk+" Truck FuelEcon Data Posted")

def getHeavyVehicleFuelEconByYears(mk, mo):
    MK = mk
    MO = mo
    print("getting fuel econ for truck "+mk+" "+mo)
    mk = mk.replace(" ","_")
    mk = mk.lower()
    mo = mo.replace(" ","_")
    mo = mo.lower()
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/truck/'+mk+'/'+mo)
    soup = BeautifulSoup(res.text, 'html.parser')
    postHeavyVehicleFuelEcon(MK, "yr", MO, "fI")
    for years in soup.find_all("ul", "model-year-summary"):
        year = years.find("li", "summary-year").get_text()
        fuelInfo = years.find("li", "summary-avg").get_text()
        postHeavyVehicleFuelEcon(MK, year, MO, fuelInfo)

def postAtvUtvFuelEcon(mk, yr, mo, fI):
    result = firebase.put('/Atvs/FuelEcon/'+mo+"/"+yr, name="Make",data=mk)
    result = firebase.put('/Atvs/FuelEcon/'+mo+"/"+yr, name="Model",data=mo)
    result = firebase.put('/Atvs/FuelEcon/'+mo+"/"+yr, name="FuelEcon",data=fI)
    print(yr+" "+mo+" "+mk+" Atv FuelEcon Data Posted "+fI)

def getAtvUtvFuelEconByYears(mk, mo):
    MK = mk
    MO = mo
    print("getting fuel econ data for atv "+mk+" "+mo)
    mk = mk.replace(" ","_")
    mk = mk.lower()
    mo = mo.replace(" ","_")
    mo = mo.lower()
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/atv/'+mk+'/'+mo)
    soup = BeautifulSoup(res.text, 'html.parser')
    postAtvUtvFuelEcon(MK, "yr", MO, "fI")
    for years in soup.find_all("ul", "model-year-summary"):
        year = years.find("li", "summary-year").get_text()
        fuelInfo = years.find("li", "summary-avg").get_text()
        postAtvUtvFuelEcon(MK, year, MO, fuelInfo)

def postCarMake(mk):
    result = firebase.put('/Cars/Makes/', name=mk,data=mk)
    print(mk+" Car Make Posted")

def getCarMakes():
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/car')
    soup = BeautifulSoup(res.text, 'html.parser')
    for make in soup.find_all("h4", "make-header"):
        mk = make.get_text().replace(".", " DOT ")
        mk = mk.replace("/", " SLASH ")
        mk = mk.replace("$", " DOLLARSIGN ")
        mk = mk.replace("#", " HASH ")
        if CarMakeHasM(mk) == -1:
            postCarMake(mk)
            appendCarMake(mk)

def postCarModel(mk, mo):
    result = firebase.put('/Cars/Models/'+mo, name="Make" , data=mk)
    print(mk+" "+mo+" Car model posted")

def getCarModels():
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/car')
    soup = BeautifulSoup(res.text, 'html.parser')
    for models in soup.find_all("ul", "models-list"):
        for model in models.findChildren():
            for name in model.findChildren():
                mo = name.get_text().replace(".", " DOT ")
                mo = mo.replace("/", " SLASH ")
                mo = mo.replace("$", " DOLLARSIGN ")
                mo = mo.replace("#", " HASH ")

                if CarModelHasMo(mo) == -1:
                    postCarModel(CarMake[i], mo)
                if CarFuelEconHasMo(mo) == -1:
                    getCarFuelEconByYears(CarMake[i], mo)
                    # print result
                    print(mo)
                    time.sleep(30)
                else:
                    print("Passing car "+ CarMake[i] +" "+mo)
        incrementCounter()
    resetCounter()
    print("--")

def postMotorcycleMake(mk):
    result = firebase.put('/Motorcycles/Makes/', name=mk,data=mk)
    print(mk+" Motorcycle Make Posted")

def getMotorcycleMakes():
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/motorcycle')
    soup = BeautifulSoup(res.text, 'html.parser')
    for make in soup.find_all("h4", "make-header"):
        mk = make.get_text().replace(".", " DOT ")
        mk = mk.replace("/", " SLASH ")
        mk = mk.replace("$", " DOLLARSIGN ")
        mk = mk.replace("#", " HASH ")
        if MotorcycleMakeHasM(mk) == -1:
            postMotorcycleMake(mk)
            appendMotorcycleMake(mk)

def postMotorcycleModel(mk, mo):
    result = firebase.put('/Motorcycles/Models/'+mo, name="Make" , data=mk)
    print(mk+" "+mo+" Motorcycle model posted")

def getMotorcycleModels():
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/motorcycle')
    soup = BeautifulSoup(res.text, 'html.parser')
    for models in soup.find_all("ul", "models-list"):
        for model in models.findChildren():
            for name in model.findChildren():
                mo = name.get_text().replace(".", " DOT ")
                mo = mo.replace("/", " SLASH ")
                mo = mo.replace("$", " DOLLARSIGN ")
                mo = mo.replace("#", " HASH ")
                if  MotorcycleModelHasMo(mo) == -1:
                    postMotorcycleModel(MotorcycleMake[i], mo)
                if  MotorcycleFuelEconHasMo(mo)==-1:
                    getMotorcycleFuelEconByYears(MotorcycleMake[i], mo)
                    # print result
                    print(mo)
                    time.sleep(30)
                else:
                    print("Passing motorcycle "+ MotorcycleMake[i] +" "+mo)

        incrementCounter()
    resetCounter()
    print("--")

def postHeavyVehicleMake(mk):
    result = firebase.put('/HeavyVehicles/Makes/', name=mk,data=mk)
    print(mk+" Truck Make Posted")

def getHeavyVehicleMakes():
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/truck')
    soup = BeautifulSoup(res.text, 'html.parser')
    for make in soup.find_all("h4", "make-header"):
        mk = make.get_text().replace(".", " DOT ")
        mk = mk.replace("/", " SLASH ")
        mk = mk.replace("$", " DOLLARSIGN ")
        mk = mk.replace("#", " HASH ")
        if HeavyVehicleMakeHasM(mk) == -1:
            postHeavyVehicleMake(mk)
            appendHeavyVehiclesMake(mk)

def postHeavyVehicleModel(mk, mo):
    result = firebase.put('/HeavyVehicles/Models/'+mo, name="Make" , data=mk)
    print(mk+" "+mo+" Truck model posted")

def getHeavyVehicleModels():
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/truck')
    soup = BeautifulSoup(res.text, 'html.parser')
    for models in soup.find_all("ul", "models-list"):
        for model in models.findChildren():
            for name in model.findChildren():
                mo = name.get_text().replace(".", " DOT ")
                mo = mo.replace("/", " SLASH ")
                mo = mo.replace("$", " DOLLARSIGN ")
                mo = mo.replace("#", " HASH ")
                if  HeavyVehicleModelHasMo(mo) == -1:
                    postHeavyVehicleModel(HeavyVehicleMake[i], mo)
                if  HeavyVehicleFuelEconHasMo(mo) == -1:
                    getHeavyVehicleFuelEconByYears(HeavyVehicleMake[i], mo)
                    # print result
                    print(mo)
                    time.sleep(30)
                else:
                    print("Passing Truck "+ HeavyVehicleMake[i] +" "+mo)

        incrementCounter()
    resetCounter()
    print("--")

def postAtvMake(mk):
    result = firebase.put('/Atvs/Makes/', name=mk,data=mk)
    print(mk+" Atv Make Posted")

def getAtvMakes():
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/atv')
    soup = BeautifulSoup(res.text, 'html.parser')
    for make in soup.find_all("h4", "make-header"):
        mk = make.get_text().replace(".", " DOT ")
        mk = mk.replace("/", " SLASH ")
        mk = mk.replace("$", " DOLLARSIGN ")
        mk = mk.replace("#", " HASH ")
        if AtvMakeHasM(mk) == -1:
            postAtvMake(mk)
            appendAtvsUtvsMake(mk)

def postAtvModel(mk, mo):
    result = firebase.put('/Atvs/Models/'+mo, name="Make" , data=mk)
    print(mk+" "+mo+" Atv model posted")

def getAtvModels():
    res = requests.get('https://web.archive.org/web/20190623094615/http://www.fuelly.com/atv')
    soup = BeautifulSoup(res.text, 'html.parser')
    for models in soup.find_all("ul", "models-list"):
        for model in models.findChildren():
            for name in model.findChildren():
                mo = name.get_text().replace(".", " DOT ")
                mo = mo.replace("/", " SLASH ")
                mo = mo.replace("$", " DOLLARSIGN ")
                mo = mo.replace("#", " HASH ")
                if AtvModelHasMo(mo) == -1:
                    postAtvModel(Atv_UtvMake[i], mo)
                if AtvFuelEconHasMo(mo)==-1:
                    getAtvUtvFuelEconByYears(Atv_UtvMake[i], mo)
                    # print result
                    print(mo)
                    time.sleep(30)
                else:
                    print("Passing Atv "+ Atv_UtvMake[i] +" "+mo)

        incrementCounter()
    resetCounter()
    print("--")

def __init__():
    createDummyEntriesFuelEconDB()
    checkAllFuelEconDB()
    checkAllModelDB()
    # checkAllMakeDB()
    # print("Retrieiving Car Makes")
    # getCarMakes()
    # print(CarMake)
    # print("Retrieved Car Makes. Sleeping for 30 Seconds.")
    # time.sleep(30)
    # print("Retrieiving Car Models")
    # getCarModels()
    # print("Retrieved Car Models. Sleeping for 5 Minutes.")
    # time.sleep(300)
    print("Retrieiving Motorcycle Makes")
    getMotorcycleMakes()
    print("Retrieved Motorcycle Makes. Sleeping for 30 Seconds.")
    time.sleep(30)
    print("Retrieiving Motorcycle Models")
    getMotorcycleModels()
    print("Retrieved Motorcycle Models. Sleeping for 5 Minutes.")
    time.sleep(300)
    print("Retrieiving Atv Makes")
    getAtvMakes()
    print("Retrieved Atv Makes. Sleeping for 30 Seconds.")
    time.sleep(30)
    print("Retrieiving Atv Models")
    getAtvModels()
    print("Retrieved Atv Models. Sleeping for 5 Minutes.")
    time.sleep(300)
    print("Retrieiving Truck Makes")
    getHeavyVehicleMakes()
    print("Retrieved Truck Makes. Sleeping for 30 Seconds.")
    time.sleep(30)
    print("Retrieiving Truck Models")
    getHeavyVehicleModels()
    print("Retrieved Truck Models.")
    print("Success! All Done! HOOORAHHHH!")

__init__()
