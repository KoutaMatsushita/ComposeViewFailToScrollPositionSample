package dev.mk2481.cn.cwi

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.mk2481.cn.cwi.ui.compose.CardItem
import dev.mk2481.cn.cwi.ui.theme.CwiTheme
import kotlin.math.roundToInt

class RecyclerViewFragment : Fragment() {
    private val recyclerViewId = ViewCompat.generateViewId()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = RecyclerView(requireContext()).apply {
        id = recyclerViewId
        fitsSystemWindows = true
        layoutManager = LinearLayoutManager(requireContext())
        addItemDecoration(SpaceItemDecoration(requireContext(), dp = 16))
        adapter = Adapter(
            context = requireContext(),
            onClick = { setFragmentResult("GO_DETAIL", bundleOf()) }
        ).apply {
            submitList(List(10) { it })
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }
}

private class SpaceItemDecoration(
    private val context: Context,
    private val dp: Int,
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val space = (dp * context.resources.displayMetrics.density).roundToInt()
        val currentPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        val lastPosition = parent.adapter?.itemCount?.minus(1) ?: -1
        outRect.set(
            space,
            space,
            space,
            space.takeIf { currentPosition == lastPosition } ?: 0
        )
    }
}

private val diffCallBack = object : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
}

private class Adapter(
    private val context: Context,
    private val onClick: () -> Unit
) : ListAdapter<Int, VH>(diffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(ComposeView(context))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.composeView.apply {
            setContent {
                CwiTheme {
                    Surface {
                        CardItem(
                            text = getItem(position).toString(),
                            onClick = onClick,
                        )
                    }
                }
            }
        }
    }
}

private class VH(val composeView: ComposeView) : ViewHolder(composeView)
