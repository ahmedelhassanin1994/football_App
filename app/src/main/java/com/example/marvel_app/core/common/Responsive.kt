package com.example.marvel_app.core.common
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun rememberWindowInfo() :WindowInfo{

    val configuration= LocalConfiguration.current

    return WindowInfo(
        ScreenWidthInfo = when {
            configuration.screenWidthDp<600-> WindowInfo.WindowType.Compact
            configuration.screenWidthDp<840-> WindowInfo.WindowType.Mediun
           else ->WindowInfo.WindowType.Expanded
        },
        ScreenHeightInfo = when {
            configuration.screenHeightDp<480-> WindowInfo.WindowType.Compact
            configuration.screenHeightDp<900-> WindowInfo.WindowType.Mediun
            else ->WindowInfo.WindowType.Expanded
        },

        ScreenWidth =configuration.screenWidthDp.dp,
        ScreenHeight = configuration.screenHeightDp.dp

    )

}

data class WindowInfo(
    val ScreenWidthInfo:WindowType,
    val ScreenHeightInfo:WindowType,
    val ScreenWidth:Dp,
    val ScreenHeight:Dp
){

    sealed class WindowType{
        object Compact:WindowType()
        object Mediun :WindowType()
        object Expanded:WindowType()
    }


}