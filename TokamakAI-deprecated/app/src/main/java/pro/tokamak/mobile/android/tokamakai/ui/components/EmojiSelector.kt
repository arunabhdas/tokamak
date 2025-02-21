package pro.tokamak.mobile.android.tokamakai.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 * Modal presentation of a bottom sheet for purposes of choosing an emoji.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmojiSelector(
    onEmojiSelected: (emoji: String) -> Unit,
    onSheetClosed: () -> Unit
) {
    val bottomSheetState = rememberSheetState(skipHalfExpanded = false)

    ModalBottomSheet(
        onDismissRequest = onSheetClosed,
        sheetState = bottomSheetState,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 64.dp)
        ) {
            val emojis = ALL_EMOJIS
            items(emojis.size) {
                Text(
                    text = emojis[it],
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable {
                        onEmojiSelected(emojis[it])
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun EmojiSelectorPreview() {
    var selectedEmoji: String? by rememberSaveable { mutableStateOf(null) }
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    Column {
        if (selectedEmoji != null) Text(text = "Selected emoji: $selectedEmoji")

        Button(onClick = {
            openBottomSheet = !openBottomSheet
        }) {
            Text(text = "Show Emoji")
        }
    }

    if (openBottomSheet) {
        EmojiSelector(
            onEmojiSelected = {
                selectedEmoji = it
                openBottomSheet = false
            },
            onSheetClosed = {
                openBottomSheet = false
            }
        )
    }
}