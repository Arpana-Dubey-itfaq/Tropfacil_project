import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView

 fun setExpandableListViewHeight(listView: ExpandableListView, nestedScroll:NestedScrollView) {
    try {
        val listAdapter = listView.expandableListAdapter as ExpandableListAdapter
        var totalHeight = 0
        for (i in 0 until listAdapter.groupCount) {
            val listItem = listAdapter.getGroupView(i, false, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val params = listView.layoutParams
        var height = totalHeight + listView.dividerHeight * (listAdapter.groupCount - 1)
        if (height < 10) height = 200
        params.height = height
        listView.layoutParams = params
        listView.requestLayout()/*
        nestedScroll.post(Runnable {
            nestedScroll.fullScroll(
                ScrollView.FOCUS_UP
            )
        })*/
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
 fun setListViewHeight(listView: ExpandableListView, group: Int) {
    val listAdapter = listView.expandableListAdapter as ExpandableListAdapter
    var totalHeight = 0
    val desiredWidth = View.MeasureSpec.makeMeasureSpec(
        listView.width,
        View.MeasureSpec.EXACTLY
    )
    for (i in 0 until listAdapter.groupCount) {
        val groupItem = listAdapter.getGroupView(i, false, null, listView)
        groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
        totalHeight += groupItem.measuredHeight
        if (listView.isGroupExpanded(i) && i != group
            || !listView.isGroupExpanded(i) && i == group
        ) {
            for (j in 0 until listAdapter.getChildrenCount(i)) {
                val listItem = listAdapter.getChildView(
                    i, j, false, null,
                    listView
                )
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
                totalHeight += listItem.measuredHeight
            }
            //Add Divider Height
            totalHeight += listView.dividerHeight * (listAdapter.getChildrenCount(i) - 1)
        }
    }
    //Add Divider Height
    totalHeight += listView.dividerHeight * (listAdapter.groupCount - 1)
    val params = listView.layoutParams
    var height = (totalHeight
            + listView.dividerHeight * (listAdapter.groupCount - 1))
    if (height < 10) height = 200
    params.height = height
    listView.layoutParams = params
    listView.requestLayout()
}
