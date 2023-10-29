package vn.anttrack.client

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import org.traccar.client.MainFragment
import org.traccar.client.R

class AKMainFragment : MainFragment() {

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)
        showDialogForInformationOfPermissionNeed()
    }


    private fun showDialogForInformationOfPermissionNeed() {

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            DialogUtil.showAlertWith1Button(
                activity,
                "",
                getString(R.string.permission_background_location_request_message),
                getString(R.string.understood)
            ) { dialog, _ ->
                dialog.dismiss()
            }
        }

    }

}