package com.example.user.implicitlntents

import android.content.Intent
import android.net.Uri
import android.support.v4.app.ShareCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    // EditText view for the website URI
    private var mWebsiteEditText: EditText? = null
    // EditText view for the location URI
    private var mLocationEditText: EditText? = null
    // EditText view for the share text
    private var mShareTextEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWebsiteEditText = findViewById(R.id.website_edittext) as EditText
        mLocationEditText = findViewById(R.id.location_editext) as EditText
        mShareTextEditText = findViewById(R.id.share_edittext) as EditText
    }

    /**

     * Maneja el onClick para el botón "Abrir sitio web". Obtiene el URI
     * del texto de edición y envía un intento implícito para esa URL.
     *
     * @param view La vista (botón) en la que se hizo clic.
     */
    fun openWebsite(view: View) {
        // Get the URL text.
        val url = mWebsiteEditText!!.text.toString()

        // Parse the URI and create the intent.
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    /**
     * Maneja el onClick para el botón "Abrir ubicación". Obtiene la ubicación
     * texto del texto de edición y envía un intento implícito para esa ubicación.
     *
     * El texto de la ubicación puede ser cualquier ubicación geográfica de búsqueda.
     *
     * @param view La vista (botón) en la que se hizo clic.
     */
    fun openLocation(view: View) {
        // Get the string indicating a location.  Input is not validated; it is
        // passed to the location handler intact.
        val loc = mLocationEditText!!.text.toString()

        // Parse the location and create the intent.
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)

        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    /**

     * Maneja el onClick para el botón "Compartir este texto". los
     * El intento implícito aquí es creado por [ShareCompat.IntentBuilder]
     * clase. Aparece un selector de aplicaciones con las opciones disponibles para compartir.
     *
     * ShareCompat.IntentBuilder es de la Biblioteca de soporte v4.
     *
     * @param view La vista (botón) en la que se hizo clic.
     */
    fun shareText(view: View) {
        // Get the shared text.
        val txt = mShareTextEditText!!.text.toString()

        // Build the share intent with the mimetype text/plain and launch
        // a chooser for the user to pick an app.
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser()
    }
}