//
//  ContentView.swift
//  iosApp
//
//  Created by  Arsen on 11/2/25.
//

import Foundation
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
    }
}

#Preview {
    ContentView()
}
