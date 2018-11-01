# Anko-Layout
KADE Anko Layout Example

Ini Hanya untuk digunakan sebagai Referensi KADE Submission 1.

Project ini berdasarkan Modul Latihan: Menampilkan Array ke Dalam RecyclerView tetapi memakai anko baik untuk common dan Layoutnya dan menambahkan Detail Activity.

Pertama, tambahkan $anko_version ke dalam berkas gradle pada level proyek:

    ext.anko_version = '0.10.5'

Kemudian pada build.grade level app, tambahkan dependencies yang dibutuhkan dari Anko:

    dependencies {
        implementation "org.jetbrains.anko:anko:$anko_version"
    }
    
dan juga tambahkan untuk Anko recycler view dan Anko Support:

    dependencies {
        implementation "org.jetbrains.anko:anko-recyclerview-v7:$anko_version"
        implementation 'com.android.support:recyclerview-v7:28.0.0'
    }
    
Untuk penerapan Anko Layoutnya di activity atau Fragment, lebih baik menggunakan AnkoComponent, 
https://github.com/Kotlin/anko/wiki/Anko-Layouts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout{
                recyclerView {
                    id = R.id.club_list
                }.lparams(width = matchParent)
            }
        }
    }

dan untuk content, 

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(ItemUi().createView(AnkoContext.create(parent.context, parent)))
            
            ...
    
    class ItemUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            relativeLayout {
                ...
            }
        }
    }
    
    
    
    
