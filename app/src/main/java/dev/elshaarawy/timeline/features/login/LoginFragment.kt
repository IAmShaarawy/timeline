package dev.elshaarawy.timeline.features.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val viewModel: LoginViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.materialButton.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                viewModel.phone.value!!,
                1,
                TimeUnit.MINUTES,
                activity!!,
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        FirebaseAuth.getInstance().signInWithCredential(p0).addOnCompleteListener {
                            if (it.isSuccessful) {
                                findNavController().navigate(
                                    LoginFragmentDirections.actionLoginFragmentToTimelineFragment()
                                )
                            }
                        }
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        Timber.e(p0)
                    }

                    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                        super.onCodeSent(p0, p1)
                        Timber.e(p0)
                    }
                })
        }
    }

    override fun LoginViewModel.observeViewModel() = Unit
}