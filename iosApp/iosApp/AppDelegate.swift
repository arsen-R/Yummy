//
//  AppDelegate.swift
//  iosApp
//
//  Created by  Arsen on 11/30/25.
//
import UIKit
import Foundation
import Firebase

class AppDelegate: NSObject, UIApplicationDelegate {

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
        FirebaseApp.configure()
        return true
    }
}
