package com.byteroll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.byteroll.myapplication.R
import com.byteroll.myapplication.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val SCREEN_SHOT_ID = "shot_id"
    }

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.profileContent.text =
            "type of action to perform in the entry service. As there exist hun\u0002dreds of entry services in WeChat’s microservice architecture, the\n" +
                    "number of different actions to perform in the entry services is of\n" +
                    "hundreds. The business priorities are predefined and stored in a\n" +
                    "hash table, which is replicated to all the WeChat backend servers\n" +
                    "that host the entry services. An item in the hash table records the\n" +
                    "mapping from an action ID (representing a distinct type of action)\n" +
                    "to a priority value. Figure 3 illustrates the logical structure of the\n" +
                    "action-priority hash table. Note that the hash table does not con\u0002tain all types of actions. By default, action types that are missing\n" +
                    "in the hash table correspond to the lowest priority. Only those in\u0002tentionally prioritized action types are recorded in the hash table,\n" +
                    "with smaller priority value indicating higher priority of the action.\n" +
                    "This results in the hash table containing only a few tens of entries.\n" +
                    "Since the set of prioritized actions to perform in the entry service\n" +
                    "are empirically stable, the hash table remains compact with rare\n" +
                    "changes despite the rapid evolution of WeChat business.\n" +
                    "Whenever a service request triggers a subsequent request to the\n" +
                    "downstream service, the business priority value is copied to the\n" +
                    "downstream request. By recursion, service requests belonging to\n" +
                    "the same call path share an identical business priority. This is based\n" +
                    "on the presumption that the success of any service request relies\n" +
                    "on the conjunctive success of all its subsequent requests to the\n" +
                    "downstream services. As the business priority is independent to\n" +
                    "the business logic of any service, DAGOR’s service admission con\u0002trol based on the business priority is service agnostic. Moreover,\n" +
                    "the above business-oriented admission control is easy to maintain,\n" +
                    "especially for the complex and highly dynamic microservice archi\u0002tecture such as the WeChat backend. On the one hand, the assign\u0002ment of business priority is done in the entry services by referring\n" +
                    "to the action-priority hash table, which is seldom changed over\n" +
                    "time4\n" +
                    ". This makes the strategy of business priority assignment rel\u0002atively stable. On the other hand, the dynamics of WeChat’s mi\u0002croservice architecture are generally reflected in the changes of\n" +
                    "basic services and leap services other than the entry services. Since\n" +
                    "the business priorities of requests to these frequently changing ser\u0002vices are inherited from the upstream service requests, developers\n" +
                    "4The action-priority hash table may be modified on the fly for performance tuning or\n" +
                    "ad-hoc service support. But this happens very rarely in the WeChat business system,\n" +
                    "e.g., once or twice per year.\n" +
                    "of these services can simply apply the functionality of business\u0002oriented admission control as a black box without concerning the\n" +
                    "setting of business priority5\n" +
                    ".\n" +
                    "4.2.2 User-oriented Admission Control. The aforementioned strat\u0002egy of business-oriented admission control constrains the decision\n" +
                    "of dropping a request to be determined by the business priority of\n" +
                    "the request. In other words, for load shedding upon service over\u0002load, the business-oriented admission control presumes requests\n" +
                    "with the same business priority are either all discarded or all con\u0002sumed by the service. However, partially discarding requests with\n" +
                    "respect to the same business priority in an overloaded service is\n" +
                    "sometimes inevitable. Such inevitability emerges when the admis\u0002sion level of business priority of the overloaded service is fluctuat\u0002ing around its “ideal optimality”. To elaborate, let us consider the\n" +
                    "following scenario where load shedding in an overloaded service\n" +
                    "is solely based on the business-oriented admission control. Sup\u0002pose the current admission level of business priority is τ but the\n" +
                    "service is still overloaded. Then the admission level is adjusted to\n" +
                    "τ − 1, i.e., all requests with business priority value greater than\n" +
                    "or equal to τ are discarded by the service. However, system soon\n" +
                    "detects that the service is underloaded with such admission level.\n" +
                    "As a consequence, the admission level is set back to τ , and then\n" +
                    "the service quickly becomes overloaded again. The above scenario\n" +
                    "continues to repeat. As a result, the related requests with business\n" +
                    "priority equal to τ are in fact partially discarded by the service in\n" +
                    "the above scenario.\n"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolBar.title = "Profile"
        val shotId = intent.getIntExtra(SCREEN_SHOT_ID, 0)
        Glide.with(this).load(shotId).into(binding.shotImageView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}