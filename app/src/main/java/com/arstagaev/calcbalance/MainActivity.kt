package com.arstagaev.calcbalance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.Directory.PACKAGE_NAME
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.format.Colour
import jxl.write.*
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private lateinit var headerOfDrawer: LinearLayout
    private lateinit var headerDrawerTitle: TextView
    private lateinit var chosenStation : TextView
    private lateinit var titleofActionBar: TextView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var mainNavDrawer: DrawerLayout
    private lateinit var toNavDrawer: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation(){
        toolbar = findViewById(R.id.toolbar)
        toolbar.navigationIcon = null
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        //navController = findNavController(R.id.main_nav_host_fragment)

        //navController.navigate(R.id.mainFragment)
    }

    /* create excel and write
    * on internal memory
     */
    private fun createExcel() {
        val sd = Environment.getExternalStorageDirectory()
        val csvFile = "KLMSEnquiry.xls"
        val directory = File("/data/data/in.kriscent.exceldemoandroid/cache")
        Toast.makeText(applicationContext, "New File", Toast.LENGTH_LONG).show()
        //create directory if not exist
        if (!directory.isDirectory) {
            directory.mkdirs()
        }
        try {
            //file path
            val file = File(directory, csvFile)
            val wbSettings = WorkbookSettings()
            wbSettings.locale = Locale("en", "EN")
            val workbook: WritableWorkbook
            workbook = Workbook.createWorkbook(file, wbSettings)

            //Excel sheet name. 0 represents first sheet
            val sheet = workbook.createSheet("userList", 0)
            sheet.setColumnView(0, 60)
            sheet.addCell(Label(0, 0, "UserName")) // column and row
            sheet.addCell(Label(1, 0, "PhoneNumber"))
            sheet.addCell(Label(0, 0, "Ram Narayan Raigar"))
            sheet.addCell(Label(1, 0, "8619713127"))
            sheet.addCell(Label(0, 1, "Hemant nayak"))
            sheet.addCell(Label(1, 1, "9001493751"))
            sheet.addCell(Label(0, 2, "Sonu raghuvanshi"))
            sheet.addCell(Label(1, 2, "8005883136"))
            val label = Label(5, 5, "Ohh May")
            val newFormat = WritableCellFormat()
            newFormat.setBackground(Colour.RED)
            label.cellFormat = newFormat
            sheet.addCell(label)
            sheet.addCell(Label(4, 4, "Ohh May"))
            //sheet.addCell(new Cell);


            //closing cursor
            workbook.write()
            workbook.close()
            Toast.makeText(application, "Data Exported in a Excel Sheet", Toast.LENGTH_SHORT).show()
        } catch (e: WriteException) {
            Timber.d("error in gen excel " + e.message)
            Toast.makeText(application, "ERROR " + e.message, Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Timber.d("error in gen excel " + e.message)
            Toast.makeText(application, "ERROR " + e.message, Toast.LENGTH_SHORT).show()
        }
    }

//    fun open_file(filename: String?) {
//        val path = File(filesDir, "dl")
//        val file = File(path, filename)
//
//        // Get URI and MIME type of file
//        val uri: Uri =
//            FileProvider.getUriForFile(this, CalcBalanceApp.PACKAGE_NAME.toString() + ".fileprovider", file)
//        val mime = contentResolver.getType(uri)
//
//        // Open file with user selected app
//        val intent = Intent()
//        intent.action = Intent.ACTION_VIEW
//        intent.setDataAndType(uri, mime)
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        startActivity(intent)
//    }
}