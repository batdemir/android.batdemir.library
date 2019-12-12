[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-23%2B-red.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![](https://jitpack.io/v/batdemirorg/android.batdemir.library.svg)](https://jitpack.io/#batdemirorg/android.batdemir.library)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/764fdb1f562044499de6efb70ee53664)](https://www.codacy.com/manual/batdemir/android.batdemir.library?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=batdemirorg/android.batdemir.library&amp;utm_campaign=Badge_Grade)
[![Bluetooth](https://api.bintray.com/packages/batdemirorg/batdemirlibrary/mybluetooth/images/download.svg?version=0.0.2) ](https://bintray.com/batdemirorg/batdemirlibrary/mybluetooth/0.0.2/link)
[![Resources](https://api.bintray.com/packages/batdemirorg/batdemirlibrary/myresources/images/download.svg?version=0.0.1) ](https://bintray.com/batdemirorg/batdemirlibrary/myresources/0.0.1/link)
[![Library](https://api.bintray.com/packages/batdemirorg/batdemirlibrary/mylibrary/images/download.svg?version=0.0.1) ](https://bintray.com/batdemirorg/batdemirlibrary/mylibrary/0.0.1/link)
[![Dialog](https://api.bintray.com/packages/batdemirorg/batdemirlibrary/mydialog/images/download.svg?version=0.0.1) ](https://bintray.com/batdemirorg/batdemirlibrary/mydialog/0.0.1/link)
[![ZebraScanner](https://api.bintray.com/packages/batdemirorg/batdemirlibrary/myscanner/images/download.svg?version=0.0.1) ](https://bintray.com/batdemirorg/batdemirlibrary/myscanner/0.0.1/link)
# Batdemir Library

## Tool
### void move(...)
*This method is used for change effectiveness.
*Activitiy means that you can use it for which activity you want to go to.
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
*This methods all about application registery. Easy use to one row.
### getInteger(...)/setInteger(...)
### getString(...)/setString(...)
### getBoolean(...)/setBoolean(...)
### getLong(...)/setLong(...)
### getFloat(...)/setFloat(...)

## ToolTimeEexpressions
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
*If extends this class, it does auto connect service and getting datas for you
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
