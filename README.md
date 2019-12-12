[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-23%2B-red.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![Bintray](https://img.shields.io/bintray/v/batdemirorg/batdemirlibrary/mylibrary?label=bintray)](https://bintray.com/beta/#/batdemirorg/batdemirlibrary?tab=packages)
[![JitPack](https://img.shields.io/jitpack/v/github/batdemirorg/android.batdemir.library?color=%23)](https://jitpack.io/#batdemirorg/android.batdemir.library)
![CII Best Practices Level](https://img.shields.io/cii/level/3493)
# Batdemir Library

## Tool
### void move(...)
*This method is used for change effectiveness.
*Activity means that you can use it for which activity you want to go to.
*Direction means if the direction is right, animation is from right to left, and animation is from right to left.
*IsStack means whether the current activity can be destroyed.
*Bundle means that you can use this example if you want to pass data to other activities.
### void anim(...)
### void animDialog(...)

## ToolConnection
*This methods all about application network connection.
### NetworkInfo getNetworkInfo(...)
### boolean isConnected(...)
### boolean isConnectedWifi(...)
### boolean isConnectedMobile(...)
### boolean isConnectedFast(...)
### boolean isConnectionFast(...)
### String getMACAddress(...)
### String getIPAddress(...)

## ToolSharedPreferences
*This methods all about application registry. Easy use to one row.
### getInteger(...)/setInteger(...)
### getString(...)/setString(...)
### getBoolean(...)/setBoolean(...)
### getLong(...)/setLong(...)
### getFloat(...)/setFloat(...)

## ToolTimeExpressions
*This methods all about casting date methods.
### setStringToDate(...)
### setDateToString(...)
### setDateFormat(...)

## MyAlertDialog
*This component default settings
### setComponentProperty(...)
### setIsCancelable(...)
### setShowCancelButton(...)
### setAutoDismiss(...)
### setShowEditText(...)
### setEditTextInputType(...)

## RetrofitClient
*This class using for service connections basic setting
### setBaseUrl(...)
### setSll(...)

## Connect
*If extends this class, it does auto connect service and getting data for you
*If connection gone wrong or some exception, it does auto exception dialog for you
*If user has not any network connection, it does auto exception dialog for you
*And it does auto async and showing progress bar for you

## License
    Copyright (c) 2019 Batuhan Demir

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
