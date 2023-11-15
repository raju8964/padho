package com.fdbanks.ui.base


import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.padho.R
import com.fdbanks.utils.SharedPref
import com.padho.utils.MProgressBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


abstract class BaseFragment<DB : ViewDataBinding> : Fragment(), View.OnClickListener {

    open lateinit var binding: DB
    var baseActivity: BaseActivity? = null
    var isLoaded = false

    var sharedPref: SharedPref? = null

    abstract fun observeViewModel()
    lateinit var progres: Dialog

    @LayoutRes
    abstract fun getLayoutRes(): Int

    private fun init(
        inflater: LayoutInflater, container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        init(inflater, container)
        super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = this
        progres = MProgressBar().showDialog(baseActivity)
        return binding.root
    }

    fun showProgress() {
        CoroutineScope(Dispatchers.Main).launch {
            progres.show()
        }
    }

    fun dismissProgress() {
        CoroutineScope(Dispatchers.Main).launch {
            progres.dismiss()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setStatusColor(R.color.white)
    }

    fun setStatusColor(color: Int) {
        changeStatusBarColor(ContextCompat.getColor(requireContext(), color))
        changeStatusBarIconColor(true)
    }

    override fun onResume() {
        super.onResume()
        isLoaded = true
        observeViewModel()

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    override fun onClick(v: View?) {
        baseActivity?.hideKeyBoard(v)
    }

    fun showToast(message: String?) {
        baseActivity?.showToast(message)
    }

    fun hideKeyBoard(input: View?) {
        input?.let {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(input.windowToken, 0)
        }
    }

    fun showKeyboard(view: View) {
        //  val view = requireActivity().currentFocus
        val methodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        assert(view != null)
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)


    }

    fun checkPermission(permission: Array<String>): Int {
        var permissionNeeded = 0
        for (i in permission.indices) {
            val result = ContextCompat.checkSelfPermission(requireActivity(), permission[i])
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionNeeded++
            }
        }
        return permissionNeeded
    }

    fun changeStatusBarColor(color: Int) {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

    fun changeStatusBarIconColor(toDark: Boolean) {
        val decor: View = requireActivity().window.decorView
        if (toDark) decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        else decor.systemUiVisibility = 0
    }

    open fun backPress() {
        findNavController().popBackStack()

    }

    open fun backPress(view: View) {
        findNavController().popBackStack()

    }

    open fun backPressWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().popBackStack()
        }, 500)
    }

    fun popupLocateView(v: View?): Rect? {
        val locInt = IntArray(2)
        if (v == null) return null
        try {
            v.getLocationOnScreen(locInt)
        } catch (npe: NullPointerException) {
            //Happens when the view doesn't exist on screen anymore.
            return null
        }
        val location = Rect()
        location.left = locInt[0]
        location.top = locInt[1]
        location.right = location.left + v.width
        location.bottom = location.top + v.height
        return location
    }


    //// run time permission
    ///  @AfterPermissionGranted(Constants.RC_CAMERA_AND_STORAGE)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun cameraStoragePermissions(): Array<String> {
        var perms: Array<String>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            perms = storge_permissions_33;
        } else {
            perms = storge_permissions;
        }
        return perms
    }

    var storge_permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    var storge_permissions_33 = arrayOf(
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.CAMERA,
    )

    fun getColor(color: Int): Int {
        return ContextCompat.getColor(requireContext(), color)
    }

}