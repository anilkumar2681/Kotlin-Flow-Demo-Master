package com.team42.kotlin_flow_demo_master

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.team42.kotlin_flow_demo_master.ui.UserScreen
import com.team42.kotlin_flow_demo_master.ui.theme.KotlinFlowDemoMasterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFlowDemoMasterTheme {
                UserScreen()
            }
        }
    }
}