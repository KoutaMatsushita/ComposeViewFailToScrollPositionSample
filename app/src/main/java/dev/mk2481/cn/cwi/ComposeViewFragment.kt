package dev.mk2481.cn.cwi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import dev.mk2481.cn.cwi.ui.compose.CardItem
import dev.mk2481.cn.cwi.ui.theme.CwiTheme

class ComposeViewFragment : Fragment() {
    private val composeViewId = ViewCompat.generateViewId()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        id = composeViewId
        fitsSystemWindows = true
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            CwiTheme {
                Surface {
                    val list = List(10) { it }
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(list, key = { it }) {
                            CardItem(text = it.toString(),
                                onClick = { setFragmentResult("GO_DETAIL", bundleOf()) })
                        }
                    }
                }
            }
        }
    }
}
