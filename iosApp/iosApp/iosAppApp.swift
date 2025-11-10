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
    init() {
        KoinKt.doInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
