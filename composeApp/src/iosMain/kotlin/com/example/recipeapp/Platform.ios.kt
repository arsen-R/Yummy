package com.example.recipeapp

import platform.UIKit.UIDevice

actual fun platform() = "it's Yummy on your ${UIDevice.currentDevice.systemName()} ${UIDevice.currentDevice.systemVersion}"