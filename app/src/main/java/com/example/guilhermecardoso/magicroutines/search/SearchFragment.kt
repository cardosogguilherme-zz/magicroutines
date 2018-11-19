package com.example.guilhermecardoso.magicroutines.search

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.guilhermecardoso.magicroutines.R
import com.example.guilhermecardoso.magicroutines.base.BaseFragment
import com.example.guilhermecardoso.magicroutines.di.buildPresenter
import com.example.guilhermecardoso.magicroutines.di.components.DaggerAppComponent
import com.example.guilhermecardoso.magicroutines.di.modules.ContextModule
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultFragment
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultPresenter
//import com.example.guilhermecardoso.magicroutines.service.service
import com.example.guilhermecardoso.magicroutines.whenNonEmpty
import javax.inject.Inject

class SearchFragment: BaseFragment(), SearchContract.View {
    lateinit var nameText: EditText
    lateinit var textText: EditText
    lateinit var cmcText: EditText
    lateinit var colorText: EditText

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflateFragment(R.layout.search_fragment, inflater)


        view.findViewById<TextView>(R.id.toolbar_title).text = "Card Search"

        nameText = view.findViewById(R.id.edit_name)
        textText = view.findViewById(R.id.edit_text)
        cmcText = view.findViewById(R.id.edit_cmc)
        colorText = view.findViewById(R.id.edit_identity)

        val searchButton = view.findViewById<ImageView>(R.id.search_button)
        searchButton.setOnClickListener {
            searchPerformed()
        }
        return view
    }

    override fun showSearchError() {
        Snackbar.make(view!!, "An internet error occured", Snackbar.LENGTH_SHORT).show()
    }

    override fun searchPerformed() {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.let {

            val fields = getFields()

            val fragment = SearchResultFragment.createFragment(fields)
            it.addToBackStack(this.javaClass.canonicalName)
            it.replace(R.id.fragment_container, fragment, SearchResultFragment::javaClass.name)
        }
        fragmentTransaction?.commit()
    }

    private fun getFields(): HashMap<String, String> {
        val fields = HashMap<String, String>()

        nameText.text.toString().whenNonEmpty { fields["name"] = this }
        textText.text.toString().whenNonEmpty { fields["text"] = this }
        cmcText.text.toString().whenNonEmpty { fields["cmc"] = this }
        colorText.text.toString().whenNonEmpty { fields["colorIdentity"] = this }

        fields["orderBy"] = "name"
        fields["page"] = "1"
        return fields
    }
}