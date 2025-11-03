//
//  ContentView.swift
//  iosApp
//
//  Created by  Arsen on 11/2/25.
//

import SwiftUI
import sharedKit

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text("Hello, \(Platform_iosKt.platform())")
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
