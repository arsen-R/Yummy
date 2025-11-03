package com.arsenr.shared

import platform.UIKit.UIDevice

actual fun platform() = "it's ${UIDevice.currentDevice.systemName()} ${UIDevice.currentDevice.systemVersion}"