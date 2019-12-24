package dev.elshaarawy.timeline.app

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository
import dev.elshaarawy.timeline.databinding.ActivityAppBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class AppActivity : AppCompatActivity() {
    private val viewModel by viewModel<AppViewModel>()
    // we use here repo due to the critical nature of accessing viewModels in attachBaseContext
    private val preferencesRepository by inject<PreferencesRepository>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityAppBinding>(
            this,
            R.layout.activity_app
        )
    }
    private val toolbar by lazy { binding.toolbar }
    private val navController by lazy { findNavController(R.id.appNavHost) }
    private val bottomNav by lazy { binding.bottomNavigationView }
    private val appBarConfiguration by lazy { AppBarConfiguration(topLevelDestination) }
    private val topLevelDestination by lazy {
        setOf(
            R.id.splashFragment,
            R.id.loginFragment,
            R.id.timelineFragment,
            R.id.settingsFragment
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setBindingVariables()
        setSupportActionBar(toolbar)
        configureNavController()
        observeViewModel()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.setAppLanguage(preferencesRepository.currentLanguage.code))
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun setBindingVariables() {
        binding.apply {
            viewModel = this@AppActivity.viewModel
            lifecycleOwner = this@AppActivity
        }
    }

    private fun configureNavController() {
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, des, _ ->
            viewModel.onDestinationChange(des.id)
        }
    }

    private fun observeViewModel() {
        MainScope().launch {
            viewModel.recreateViewChannel
                .consumeAsFlow()
                .collect {
                    recreate()
                }
        }
        viewModel.apply {
            nightMode.observe(this@AppActivity, Observer {
                delegate.localNightMode = it
            })
        }
    }

    private fun Context.setAppLanguage(code: String): Context {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            updateResources(code)
        else
            updateResourcesLegacy(code)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun Context.updateResources(language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = resources.configuration
        configuration.setLocale(locale)

        return createConfigurationContext(configuration)
    }

    private fun Context.updateResourcesLegacy(language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = resources

        val configuration = resources.configuration
        configuration.locale = locale

        resources.updateConfiguration(configuration, resources.displayMetrics)
        return this
    }

}