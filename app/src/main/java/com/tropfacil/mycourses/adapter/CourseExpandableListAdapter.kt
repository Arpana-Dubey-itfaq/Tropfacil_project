package com.tropfacil.mycourses.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.tropfacil.R
import com.tropfacil.data.Element
import com.tropfacil.model.CourseChaptersWithLessonsModel


class CourseExpandableListAdapter internal constructor(
    private val context: Context,
    private val chaptersWithLessonsList: MutableList<CourseChaptersWithLessonsModel>
) : BaseExpandableListAdapter() {
    override fun getChild(parentPosition: Int, childPosition: Int): Element? {
        return chaptersWithLessonsList[parentPosition].lessonsList?.get(childPosition)
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView = convertView
        val lessonItem = getChild(listPosition, expandedListPosition) as Element
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_course_child, null)
        }
        val lessonName = convertView!!.findViewById<TextView>(R.id.tvLessonName)
        val timeSpent = convertView!!.findViewById<TextView>(R.id.tvTimeSpent)
        val lessonProgress = convertView!!.findViewById<ProgressBar>(R.id.progressBar)
        val tvLessonProgressPercentage =
            convertView!!.findViewById<TextView>(R.id.tvProgressWithPercentage)
        val imgLessonPlayOrLock = convertView.findViewById<ImageView>(R.id.imgLessonPlayOrLock)
        //set lesson name
        lessonItem.libelle.let {
            lessonName.text = it
        }

        //calculate time for each lesson
        lessonItem.duree_estimee.let {
            if (!TextUtils.isEmpty(it)) {
                val durationInHour = it.toInt() / 3600
                val durationInMin = (it.toInt() % 3600) / 60
                val durationInSec = (it.toInt() % 3600) % 60
                timeSpent.text = when {
                    durationInHour == 0 -> {
                        "${durationInMin}min ${durationInSec}sec"
                    }
                    durationInMin == 0 -> {
                        "${durationInHour}hr ${durationInSec}sec"
                    }
                    durationInSec == 0 -> {
                        "${durationInHour}hr ${durationInMin}min"
                    }
                    else -> {
                        "${durationInHour}hr ${durationInMin}min ${durationInSec}sec"
                    }
                }
            }
        }
        //set progress bar
        lessonItem.avancement.let {
            lessonProgress.progress = it
            tvLessonProgressPercentage.text = "${it}"
        }

        //set icon play or locked for lessons
        lessonItem.verrouille.let {
            if (lessonItem.verrouille) {
                imgLessonPlayOrLock.setImageResource(R.drawable.chapter_lock)
            } else {
                imgLessonPlayOrLock.setImageResource(R.drawable.chapters_play)
            }
        }
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return chaptersWithLessonsList[listPosition].lessonsList!!.size
    }

    override fun getGroup(listPosition: Int): CourseChaptersWithLessonsModel {
        return this.chaptersWithLessonsList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.chaptersWithLessonsList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView = convertView
        val groupParentItem = getGroup(listPosition) as CourseChaptersWithLessonsModel
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_course_parent, null)
        }
        val chapterName = convertView!!.findViewById<TextView>(R.id.tvChapterName)
        val chapterIndex = convertView!!.findViewById<TextView>(R.id.tvChaptersIndex)
        val totalLessons = convertView!!.findViewById<TextView>(R.id.tvLessonsCount)
        val totalTimeForAllLessons = convertView!!.findViewById<TextView>(R.id.tvTimeSpentVal)
        val imgArrow = convertView.findViewById<ImageView>(R.id.imgArrow)
        //set chapter name
        groupParentItem.libelle.let {
            chapterName.text = it
        }
        //set chapter lable with index
        val chapterCount = listPosition + 1
        chapterIndex.text = String.format(context.getString(R.string.chapters), chapterCount)

        //set lessons count
        if (!groupParentItem.lessonsList.isNullOrEmpty()) {
            totalLessons.text = if (groupParentItem.lessonsList!!.size == 1)
                String.format(
                    context.getString(R.string.lesson_count),
                    groupParentItem.lessonsList!!.size
                )
            else String.format(
                context.getString(R.string.lessons_count),
                groupParentItem.lessonsList!!.size
            )
        }
        //set time spent for each lesson
        var lessonsDuration = 0
        if (!groupParentItem.lessonsList.isNullOrEmpty()) {
            lessonsDuration =
                groupParentItem.lessonsList!!.filter { it.duree_estimee != null && it.duree_estimee != "" }
                    .sumOf { it.duree_estimee.toInt() }
        }
        val durationInHour = lessonsDuration / 3600
        val durationInMin = (lessonsDuration % 3600) / 60
        val durationInSec = (lessonsDuration % 3600) % 60

        totalTimeForAllLessons.text = when {
            durationInHour == 0 -> {
                "${durationInMin}min ${durationInSec}sec"
            }
            durationInMin == 0 -> {
                "${durationInHour}hr ${durationInSec}sec"
            }
            durationInSec == 0 -> {
                "${durationInHour}hr ${durationInMin}min"
            }
            else -> {
                "${durationInHour}hr ${durationInMin}min ${durationInSec}sec"
            }
        }

        //set arrow down or up
        if (isExpanded)

            imgArrow.setImageResource(R.drawable.accordion_up)
        else imgArrow.setImageResource(R.drawable.accordion_down)

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}

