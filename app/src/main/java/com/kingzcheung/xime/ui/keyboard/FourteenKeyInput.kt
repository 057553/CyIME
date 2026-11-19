package com.kingzcheung.xime.ui.keyboard

import androidx.compose.runtime.staticCompositionLocalOf
import kotlin.math.roundToInt

internal val LocalFourteenKeyLayout = staticCompositionLocalOf { false }

/**
 * 内置合并键布局（qwerty_17 / qwerty_18 等）：双字母键按字母数占位，和 14 键一样对齐 26 键的网格。
 * 与 [LocalFourteenKeyLayout] 分开，避免 14 键专属行为（Shift 拆字、左右滑动选字母、14 键角标表）跟着开。
 */
internal val LocalMergedPairKeys = staticCompositionLocalOf { false }

internal fun normalizeLetterSwipeDistance(value: Float): Float =
    (((value.takeIf { it.isFinite() } ?: 24f).coerceIn(12f, 72f) / 2f).roundToInt() * 2f)

/** 14 键固定有字母对；自建布局只有合并格才有，单字母键和功能键不参与。 */
internal fun letterSwipeAvailable(fourteenKey: Boolean, customHasMergedGroups: Boolean): Boolean =
    fourteenKey || customHasMergedGroups

/** Only paired alphabet keys participate; function keys and single letters never do. */
internal fun fourteenKeyLetters(group: String?, enabled: Boolean): Pair<String, String>? =
    group?.takeIf { enabled && it.length == 2 && it.all { char -> char.lowercaseChar() in 'a'..'z' } }
        ?.let { it[0].toString() to it[1].toString() }
