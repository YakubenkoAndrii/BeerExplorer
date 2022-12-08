package com.sample.project.core_ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightGray = Color(0xFF808080)
val MediumGray = Color(0xFF404040)
val TextWhite = Color(0xFFEEEEEE)
val DarkBlue = Color(0xFF252339)
val SecondaryLabelDmColor = Color(0xFFA7A7A7)

/** APP BAR START */
val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.Black else LightGray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.Black
/** APP BAR END */

/** LABELS START */
val Colors.labelColor: Color
    @Composable
    get() = if (isLight) DarkBlue else TextWhite

/** LABELS END */

/** BACKGROUNDS START */
val Colors.cardBeerItemBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else MediumGray

val Colors.screenBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.titleBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else MediumGray

val Colors.finishBackgroundColor: Color
    @Composable
    get() = if (isLight) SecondaryLabelDmColor else DarkBlue

/** BACKGROUNDS END */

/** SCROLL INDICATORS START */
val Colors.activeIndicatorColor: Color
    @Composable
    get() = if (isLight) DarkBlue else TextWhite

val Colors.inactiveIndicatorColor: Color
    @Composable
    get() = if (isLight) LightGray else LightGray
/** SCROLL INDICATORS END */
