package android.maxim.navigation

import android.maxim.navigation.databinding.ActivityHelloWorldBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.javafaker.Faker

class HelloWorldActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelloWorldBinding

    private val faker = Faker.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloWorldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = CounterFragment.newInstance(
                counterValue = 1,
                quote = createQuote()
            )
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    fun createQuote(): String {
        return faker.lebowski().quote()
    }

    fun getScreenCount(): Int {
        return supportFragmentManager.backStackEntryCount + 1
    }
}