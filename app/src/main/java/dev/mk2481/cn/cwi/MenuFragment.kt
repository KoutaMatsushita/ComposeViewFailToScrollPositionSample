package dev.mk2481.cn.cwi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import dev.mk2481.cn.cwi.ui.compose.CardItem
import dev.mk2481.cn.cwi.ui.theme.CwiTheme

@OptIn(ExperimentalMaterial3Api::class)
class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            CwiTheme {
                Surface {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeContentPadding()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CardItem(
                            text = "COMPOSE VIEW",
                            onClick = { setFragmentResult("GO_COMPOSE_VIEW", bundleOf()) }
                        )
                        CardItem(
                            text = "RECYCLER VIEW",
                            onClick = { setFragmentResult("GO_RECYCLER_VIEW", bundleOf()) }
                        )
                    }
                }
            }
        }
    }
}
