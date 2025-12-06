//
//  iosAppApp.swift
//  iosApp
//
//  Created by  Arsen on 11/2/25.
//

import SwiftUI
import ComposeApp

@main
struct iosAppApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    init() {
        KoinApplication.start()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
