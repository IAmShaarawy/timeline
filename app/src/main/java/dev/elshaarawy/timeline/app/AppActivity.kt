package dev.elshaarawy.timeline.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.databinding.ActivityAppBinding


class AppActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityAppBinding>(
            this,
            R.layout.activity_app
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding
    }
}