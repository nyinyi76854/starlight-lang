package com.starlightlang.lib
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentContainerView
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.ImageView
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Switch
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.RatingBar
import android.widget.ImageButton
import android.widget.ToggleButton
import android.widget.Spinner
import android.webkit.WebView
import android.widget.AutoCompleteTextView
import android.widget.VideoView
import android.widget.TextClock
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.SearchView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ScrollView
import android.widget.HorizontalScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Space
import android.widget.ListView
import android.widget.GridView
import com.google.android.material.appbar.AppBarLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.card.MaterialCardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.radiobutton.MaterialRadioButton
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
fun ViewGroup.addSL(view: View) {
    this.addView(view)
}

fun Context.SLText(
    text: String,
    block: TextView.() -> Unit = {}
): TextView {
    val tv = TextView(this)
    tv.text = text
    tv.block()
    return tv
}


fun Context.SLBtn(block: Button.() -> Unit): Button {
    val btn = Button(this)
    btn.block()
    return btn
}

fun Context.SLEditText(block: EditText.() -> Unit): EditText {
    val et = EditText(this)
    et.block()
    return et
}
fun Context.SLIcon(
    name: String,
    sizeSp: Float = 24f,
    color: Int = Color.BLACK,
    block: TextView.() -> Unit = {}
): TextView {

    val tv = TextView(this)

    val typeface = ResourcesCompat.getFont(this, R.font.material_icons)

    tv.apply {
        text = name
        this.typeface = typeface
        textSize = sizeSp
        setTextColor(color)
        block()
    }

    return tv
}
fun Context.SLImage(block: ImageView.() -> Unit): ImageView {
    val iv = ImageView(this)
    iv.block()
    return iv
}

fun Context.SLCheckBox(block: CheckBox.() -> Unit): CheckBox {
    val cb = CheckBox(this)
    cb.block()
    return cb
}

fun Context.SLRadioButton(block: RadioButton.() -> Unit): RadioButton {
    val rb = RadioButton(this)
    rb.block()
    return rb
}

fun Context.SLSwitch(block: Switch.() -> Unit): Switch {
    val sw = Switch(this)
    sw.block()
    return sw
}

fun Context.SLProgress(block: ProgressBar.() -> Unit): ProgressBar {
    val pb = ProgressBar(this)
    pb.block()
    return pb
}

fun Context.SLSeekBar(block: SeekBar.() -> Unit): SeekBar {
    val sb = SeekBar(this)
    sb.block()
    return sb
}

fun Context.SLRatingBar(block: RatingBar.() -> Unit): RatingBar {
    val rb = RatingBar(this)
    rb.block()
    return rb
}
fun Context.SLImageButton(block: ImageButton.() -> Unit): ImageButton {
    val ib = ImageButton(this)
    ib.block()
    return ib
}

fun Context.SLToggleButton(block: ToggleButton.() -> Unit): ToggleButton {
    val tb = ToggleButton(this)
    tb.block()
    return tb
}

fun Context.SLSpinner(block: Spinner.() -> Unit): Spinner {
    val sp = Spinner(this)
    sp.block()
    return sp
}

fun Context.SLWebView(
    url: String? = null,
    enableJs: Boolean = true,
    block: WebView.() -> Unit = {}
): WebView {
    val webView = WebView(this)
    webView.settings.apply {
        javaScriptEnabled = enableJs
        domStorageEnabled = true
        loadWithOverviewMode = true
        useWideViewPort = true
    }
    if (url != null) webView.loadUrl(url)
    webView.block()
    return webView
}

fun Context.SLAutoCompleteTextView(block: AutoCompleteTextView.() -> Unit): AutoCompleteTextView {
    val actv = AutoCompleteTextView(this)
    actv.block()
    return actv
}
fun Context.SLFragmentContainer(
    id: Int? = null,
    block: FragmentContainerView.() -> Unit = {}
): FragmentContainerView {

    val container = FragmentContainerView(this)
    container.id = id ?: View.generateViewId()
    container.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )

    container.block()
    return container
}

fun FragmentManager.addFragment(
    containerId: Int,
    fragment: Fragment,
    tag: String? = null
) {
    this.beginTransaction()
        .setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        .replace(containerId, fragment, tag)
        .setReorderingAllowed(true)
        .commit()
}
fun Context.SLVideoView(block: VideoView.() -> Unit): VideoView {
    val vv = VideoView(this)
    vv.block()
    return vv
}

fun Context.SLTextClock(block: TextClock.() -> Unit): TextClock {
    val tc = TextClock(this)
    tc.block()
    return tc
}

fun Context.SLCalendarView(block: CalendarView.() -> Unit): CalendarView {
    val cv = CalendarView(this)
    cv.block()
    return cv
}

fun Context.SLDatePicker(block: DatePicker.() -> Unit): DatePicker {
    val dp = DatePicker(this)
    dp.block()
    return dp
}

fun Context.SLTimePicker(block: TimePicker.() -> Unit): TimePicker {
    val tp = TimePicker(this)
    tp.block()
    return tp
}

fun Context.SLSearchView(block: SearchView.() -> Unit): SearchView {
    val sv = SearchView(this)
    sv.block()
    return sv
}
fun Context.SLRelativeLayout(block: RelativeLayout.() -> Unit): RelativeLayout {
    val rl = RelativeLayout(this)
    rl.block()
    return rl
}

fun Context.SLGridLayout(block: GridLayout.() -> Unit): GridLayout {
    val gl = GridLayout(this)
    gl.block()
    return gl
}

fun Context.SLScrollView(block: ScrollView.() -> Unit): ScrollView {
    val sv = ScrollView(this)
    sv.block()
    return sv
}

fun Context.SLHorizontalScrollView(block: HorizontalScrollView.() -> Unit): HorizontalScrollView {
    val hsv = HorizontalScrollView(this)
    hsv.block()
    return hsv
}

fun Context.SLConstraintLayout(block: ConstraintLayout.() -> Unit): ConstraintLayout {
    val cl = ConstraintLayout(this)
    cl.block()
    return cl
}

fun Context.SLRecyclerView(
    layoutManager: RecyclerView.LayoutManager? = null,
    block: RecyclerView.() -> Unit
): RecyclerView {
    val rv = RecyclerView(this)
    if (layoutManager != null) rv.layoutManager = layoutManager
    rv.block()
    return rv
}

fun Context.SLCardView(block: CardView.() -> Unit): CardView {
    val cv = CardView(this)
    cv.block()
    return cv
}

fun Context.SLCoordinatorLayout(block: CoordinatorLayout.() -> Unit): CoordinatorLayout {
    val cl = CoordinatorLayout(this)
    cl.block()
    return cl
}

fun Context.SLTableLayout(block: TableLayout.() -> Unit): TableLayout {
    val tl = TableLayout(this)
    tl.block()
    return tl
}

fun Context.SLTableRow(block: TableRow.() -> Unit): TableRow {
    val tr = TableRow(this)
    tr.block()
    return tr
}

fun Context.SLSpace(block: Space.() -> Unit): Space {
    val sp = Space(this)
    sp.block()
    return sp
}
fun Context.SLListView(block: ListView.() -> Unit): ListView {
    val lv = ListView(this)
    lv.block()
    return lv
}

fun Context.SLGridView(block: GridView.() -> Unit): GridView {
    val gv = GridView(this)
    gv.block()
    return gv
}

fun Context.SLFrameLayout(block: FrameLayout.() -> Unit): FrameLayout {
    val fl = FrameLayout(this)
    fl.block()
    return fl
}

fun Context.SLAppBarLayout(block: AppBarLayout.() -> Unit): AppBarLayout {
    val abl = AppBarLayout(this)
    abl.block()
    return abl
}

fun Context.SLToolbar(block: Toolbar.() -> Unit): Toolbar {
    val tb = Toolbar(this)
    tb.block()
    return tb
}

fun Context.SLFloatingActionButton(block: FloatingActionButton.() -> Unit): FloatingActionButton {
    val fab = FloatingActionButton(this)
    fab.block()
    return fab
}

fun Context.SLTabLayout(block: TabLayout.() -> Unit): TabLayout {
    val tl = TabLayout(this)
    tl.block()
    return tl
}
fun Context.SLRow(block: LinearLayout.() -> Unit): LinearLayout = SLLinearLayout {
    orientation = LinearLayout.HORIZONTAL
    block()
}

fun Context.SLColumn(block: LinearLayout.() -> Unit): LinearLayout = SLLinearLayout {
    orientation = LinearLayout.VERTICAL
    block()
}

fun Context.SLLinearLayout(block: LinearLayout.() -> Unit): LinearLayout {
    val ll = LinearLayout(this)
    ll.block()
    return ll
}
fun Context.SLTextInputLayout(block: TextInputLayout.() -> Unit): TextInputLayout {
    val til = TextInputLayout(this)
    til.block()
    return til
}

fun Context.SLTextInputEditText(block: TextInputEditText.() -> Unit): TextInputEditText {
    val tiet = TextInputEditText(this)
    tiet.block()
    return tiet
}

fun Context.SLChip(block: Chip.() -> Unit): Chip {
    val c = Chip(this)
    c.block()
    return c
}

fun Context.SLChipGroup(block: ChipGroup.() -> Unit): ChipGroup {
    val cg = ChipGroup(this)
    cg.block()
    return cg
}
fun Context.SLMaterialCardView(block: MaterialCardView.() -> Unit): MaterialCardView {
    val mcv = MaterialCardView(this)
    mcv.block()
    return mcv
}

fun Context.SLMaterialButton(block: MaterialButton.() -> Unit): MaterialButton {
    val mb = MaterialButton(this)
    mb.block()
    return mb
}

fun Context.SLMaterialButtonToggleGroup(block: MaterialButtonToggleGroup.() -> Unit): MaterialButtonToggleGroup {
    val mbtg = MaterialButtonToggleGroup(this)
    mbtg.block()
    return mbtg
}

fun Context.SLCircularProgressIndicator(block: CircularProgressIndicator.() -> Unit): CircularProgressIndicator {
    val cpi = CircularProgressIndicator(this)
    cpi.block()
    return cpi
}

fun Context.SLLinearProgressIndicator(block: LinearProgressIndicator.() -> Unit): LinearProgressIndicator {
    val lpi = LinearProgressIndicator(this)
    lpi.block()
    return lpi
}

fun Context.SLSlider(block: Slider.() -> Unit): Slider {
    val slider = Slider(this)
    slider.block()
    return slider
}

fun Context.SLSwitchMaterial(block: SwitchMaterial.() -> Unit): SwitchMaterial {
    val sm = SwitchMaterial(this)
    sm.block()
    return sm
}

fun Context.SLMaterialCheckBox(block: MaterialCheckBox.() -> Unit): MaterialCheckBox {
    val mcb = MaterialCheckBox(this)
    mcb.block()
    return mcb
}

fun Context.SLMaterialRadioButton(block: MaterialRadioButton.() -> Unit): MaterialRadioButton {
    val mrb = MaterialRadioButton(this)
    mrb.block()
    return mrb
}
