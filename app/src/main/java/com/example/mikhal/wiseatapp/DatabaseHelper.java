package com.example.mikhal.wiseatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*************************************************DatabaseHelper.java******************************************************
 This class is responsible for working with the SqlLite DataBase
 **************************************************************************************************************************/

public class DatabaseHelper extends SQLiteOpenHelper {
    // profiles table
    public static final String WISEATAPP_DATABASE = "wiseatApp.db";
    public static final String PROFILES_TABLE = "profiles_table";
    public static final String PROFILEID = "profileID";
    public static final String BEEF = "beef";
    public static final String CHICKEN = "chicken";
    public static final String PORK = "pork";
    public static final String FISH = "fish";
    public static final String INSECTS = "Insects";
    public static final String EGGS = "eggs";
    public static final String MILK = "milk";
    public static final String HONEY = "honey";
    public static final String GLUTEN = "gluten";
    public static final String LUPIN = "lupin";
    public static final String SESAME = "sesame";
    public static final String ALGAE = "algae";
    public static final String SHELLFISH = "shellfish";
    public static final String SOY = "soy";
    public static final String PEANUTS = "peanuts";
    public static final String SULPHITE = "sulphite";
    public static final String NUTS = "nuts";
    public static final String MUSTARD = "mustard";
    public static final String CELERY = "celery";
    public static final String CORN = "corn";

    // users table
    public static final String USERS_TABLE = "usersTable";
    public static final String USER_ID = "userId";
    public static final String IS_ACTIVE = "isActive";

    //ingredients table
    public static  final String ING_TABLE = "ingredientsTable";
    public static  final  String INGREDIENT = "ingredient";
    public static final String FAMILY = "family";

    //recovery table
    public static final String RECOVERY_TABLE = "recovery_table";
    public static final String R_PROFILEID = "profileID";
    public static final String IS_NEVER = "isNever";
    public static final String R_BEEF = "beef";
    public static final String R_CHICKEN = "chicken";
    public static final String R_PORK = "pork";
    public static final String R_FISH = "fish";
    public static final String R_INSECTS = "insects";
    public static final String R_EGGS = "eggs";
    public static final String R_MILK = "milk";
    public static final String R_HONEY = "honey";
    public static final String R_GLUTEN = "gluten";
    public static final String R_LUPIN = "lupin";
    public static final String R_SESAME = "sesame";
    public static final String R_ALGAE = "algae";
    public static final String R_SHELLFISH = "shellfish";
    public static final String R_SOY = "soy";
    public static final String R_PEANUTS = "peanuts";
    public static final String R_SULPHITE = "sulphite";
    public static final String R_NUTS = "nuts";
    public static final String R_MUSTARD = "mustard";
    public static final String R_CELERY = "celery";
    public static final String R_CORN = "corn";


    public DatabaseHelper(Context context) {
        super(context, WISEATAPP_DATABASE, null, 4);
    }

    /*creates Our Tables: Users Table (contains the users accounts and their profile id),Ingredients Table (contains the ingredients
    and their families),Profile Tables (contains all users' profiles),Recovery Table (contains the searches statistics in order to create
    recommendations for the users).*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USERS_TABLE + " (userId TEXT PRIMARY KEY, profileID INTEGER, isActive INTEGER)");
        db.execSQL("create table " + ING_TABLE + " (ingredient TEXT, family TEXT)");
        db.execSQL("create table " + PROFILES_TABLE +" (profileID INTEGER PRIMARY KEY autoincrement,beef INTEGER,chicken INTEGER,pork INTEGER,fish INTEGER,Insects INTEGER,eggs INTEGER,milk INTEGER,honey INTEGER,gluten INTEGER,lupin INTEGER,sesame INTEGER,algae INTEGER,shellfish INTEGER,soy INTEGER,peanuts INTEGER,sulphite INTEGER,nuts INTEGER,mustard INTEGER,celery INTEGER,corn INTEGER)");
        db.execSQL("create table " + RECOVERY_TABLE +" (profileID INTEGER,isNever INTEGER,beef INTEGER,chicken INTEGER,pork INTEGER,fish INTEGER,Insects INTEGER,eggs INTEGER,milk INTEGER,honey INTEGER,gluten INTEGER,lupin INTEGER,sesame INTEGER,algae INTEGER,shellfish INTEGER,soy INTEGER,peanuts INTEGER,sulphite INTEGER,nuts INTEGER,mustard INTEGER,celery INTEGER,corn INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+PROFILES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+RECOVERY_TABLE);
        onCreate(db);
    }

    //// insert data to the Profile Table
    public boolean insertData(Integer beef,Integer chicken,Integer pork,Integer fish,Integer Insects, Integer eggs,Integer milk,Integer honey,Integer gluten,Integer lupin,Integer sesame, Integer algae, Integer shellfish, Integer soy, Integer peanuts,Integer sulphite,Integer nuts, Integer mustard,Integer celery, Integer corn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BEEF,beef);
        contentValues.put(CHICKEN,chicken);
        contentValues.put(PORK,pork);
        contentValues.put(FISH,fish);
        contentValues.put(INSECTS,Insects);
        contentValues.put(EGGS,eggs);
        contentValues.put(MILK,milk);
        contentValues.put(HONEY,honey);
        contentValues.put(GLUTEN,gluten);
        contentValues.put(LUPIN,lupin);
        contentValues.put(SESAME,sesame);
        contentValues.put(ALGAE,algae);
        contentValues.put(SHELLFISH,shellfish);
        contentValues.put(SOY,soy);
        contentValues.put(PEANUTS,peanuts);
        contentValues.put(SULPHITE,sulphite);
        contentValues.put(NUTS,nuts);
        contentValues.put(MUSTARD,mustard);
        contentValues.put(CELERY,celery);
        contentValues.put(CORN,corn);

        long result = db.insert(PROFILES_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // insert new user id to the Users Table and update the active user to the current user
    public boolean insertUIDToUsers(String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID,userId);
        contentValues.put(IS_ACTIVE,1);

        long result = db.insert(USERS_TABLE,null ,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // insert new user id to the Users Table and update the active user to the current user
    public void deactivateAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IS_ACTIVE,0);
        db.update("usersTable",contentValues,null,null);
    }

    //match the created/updated profile
    public void matchProfileToUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select MAX(profileID) from profiles_table", null);
        if(res.getCount()!=0) {
            res.moveToFirst();
            int data = res.getInt(0);

            ContentValues contentValues = new ContentValues();
            contentValues.put(PROFILEID,data);

            db.update("usersTable",contentValues,"isActive=1",null);
        }
    }


    //active the current user
    public void activateUser (String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IS_ACTIVE,1);

        db.update("usersTable",contentValues,"userId='"+userId+"'",null);
    }

    //check id user already exists or this is a new one
    public boolean checkIfUIDExist(String userId) {
        //query that checks if the userId exist
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from usersTable where userId='"+userId+"'", null);

        if(res.getCount()==0)
            return false;
        else
            return true;
    }

   /* public void truncateTables()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + PROFILES_TABLE);
        db.execSQL("delete from " + USERS_TABLE);
    }*/

    //initialize the database with ingredients
    public void insertING2DB()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        String []s = {"acacia gum,null",
                "acesulfame potassium,null",
                "acetic acid,null",
                "acetylated distarch adipate,null",
                "acetylated distarch glycerol,null",
                "acetylated distarch oxypropanol,null",
                "acetylated distarch phosphate,null",
                "acid hydrolyzed proteins,null",
                "acid modified starch,null",
                "aconitic acid,null",
                "adipic acid,null",
                "agar-agar,algae",
                "almond butter,nuts",
                "almond flour,nuts",
                "almonds,nuts",
                "alpha-tocopherol acetate,null",
                "aluminum ammonium sulfate,null",
                "aluminum calcium silicate,null",
                "aluminum hydroxide,null",
                "aluminum oleate,null",
                "aluminum oleate (packaging),null",
                "aluminum palmitate (packaging),null",
                "aluminum sodium sulfate,null",
                "aluminum sulfate,null",
                "ammoniated glycyrrhizin,null",
                "ammonium alginate,null",
                "ammonium bicarbonate,null",
                "ammonium carbonate,null",
                "ammonium chloride,null",
                "ammonium citrate,null",
                "ammonium hydroxide,null",
                "ammonium phosphate dibasic (report 32),null",
                "ammonium phosphate dibasic (report 34),null",
                "ammonium phosphate monobasic (report 32),null",
                "ammonium phosphate monobasic (report 34),null",
                "ammonium sulfate,null",
                "annatto,null",
                "annatto color,null",
                "annatto extract color,null",
                "annatto extract for color,null",
                "apocarotenal,null",
                "apo-carotenal,null",
                "apple,null",
                "apple cider vinegar,null",
                "apple juice concentrate,null",
                "apple puree concentrate,null",
                "arrowroot starch,null",
                "artificial flavor,null",
                "artificial flavors,null",
                "ascorbic acid,null",
                "ascorbyl palmitate (palmitoyl l-ascorbic),null",
                "autolyzed yeast,null",
                "autolyzed yeast extract,null",
                "azodicarbonamide,null",
                "bacon,pork",
                "bacon (cured with water,pork",
                "bacon cured with: water,pork",
                "bacon is cured with: water,pork",
                "baking soda,null",
                "barbecue sauce: molasses,mustard",
                "barley malt,gluten",
                "barley malt extract,gluten",
                "basil,null",
                "battered with: enriched bleached wheat flour (enriched with niacin,gluten",
                "bay leaf,null",
                "beef base,beef",
                "beef extract,beef",
                "beef fat flavor,beef",
                "beef flavor [autolyzed yeast extract,beef",
                "beef stock,beef",
                "beer,gluten",
                "beeswax (yellow or white),honey",
                "bentonite,null",
                "benzoic acid,null",
                "bha,null",
                "bht,null",
                "bht for freshness,null",
                "bicarbonate,null",
                "biotin,null",
                "black cumin,null",
                "acidity regulators (e450,null",
                "e327,null",
                "e263,null",
                "e339) flavorings,null",
                "food coloring (carrot extract),null",
                "food coloring (carrot extract) antioxidants (e300,null",
                "strawberry puree (39%),null",
                "blackberry puree (11%),null",
                "strawbery puree (39%),null",
                "blackberry puree (1 1%),null",
                "gloucose syrup,null",
                "gloucose syru,null",
                "gloucose syru natural food colours (concentrated black carrot juice,null",
                "glucose syrup,null",
                "glucose syru,null",
                "glucose syru natural food colours (concentrated black carrot juice,null",
                "natural food colours (concentrated black carrot juice,null",
                "concentrated elderbery juice,null",
                "concentrated elderberry juice,null",
                "beetroot red),null",
                "stabilizers (locust bean gum,null",
                "e466,null",
                "guar gum),null",
                "emulsifier (e471),null",
                "edible acid (citric acid),null",
                "antioxidants (e300,null",
                "e307) may contain traces of soy,soy",
                "black pepper,null",
                "bleached starch,null",
                "blue 1,null",
                "blue 1 lake,null",
                "blue 2 lake,null",
                "blueberry,null",
                "bread crumbs (flour [unbleached wheat flour,gluten",
                "bread crumbs (unenriched unbleached flour,gluten",
                "breadcrumbs (bleached wheat flour,gluten",
                "breaded with: bleached wheat flour,gluten",
                "breaded with: enriched bleached wheat flour (enriched with niacin,gluten",
                "breaded with: enriched bleached wheat flour (niacin,gluten",
                "breaded with: enriched flour (wheat flour,gluten",
                "breaded with: enriched wheat flour (niacin,gluten",
                "breaded with: enriched wheat flour (wheat flour,gluten",
                "breading set in soybean oil,soy",
                "brown algae,algae",
                "brown rice syrup,null",
                "brown sugar,null",
                "bun ingredients: enriched unbleached wheat flour (wheat flour,gluten",
                "butter,milk",
                "butter (cream,milk",
                "butter (pasteurized sweet cream,milk",
                "buttermilk,milk",
                "buttermilk blend (buttermilk,milk",
                "buttermilk powder (sweet cream,milk",
                "butylated hydroxyanisole (bha),null",
                "butylated hydroxytoluene (bht),null",
                "caffeine,null",
                "calcium acetate,null",
                "calcium alginate,algae",
                "calcium carbonate,null",
                "calcium caseinate,milk",
                "calcium chloride,null",
                "calcium citrate,null",
                "calcium disodium edta [protects flavor],null",
                "calcium gluconate,null",
                "calcium glycerophosphate,null",
                "calcium glycerophosphate (packaging),null",
                "calcium hexametaphosphate,null",
                "calcium hydroxide,null",
                "calcium hypophosphite,null",
                "calcium iodate,null",
                "calcium lactate,null",
                "calcium l-ascorbate,null",
                "calcium oxide,null",
                "calcium pantothenate,null",
                "calcium phosphate,null",
                "calcium phosphate dibasic,null",
                "calcium phosphate monobasic,null",
                "calcium phosphate tribasic,null",
                "calcium phytate,null",
                "calcium propionate,null",
                "calcium pyrophosphate,null",
                "calcium silicate,null",
                "calcium sodium edta [protects flavor] natural flavor,null",
                "calcium sorbate,null",
                "calcium stearate,null",
                "calcium sulfate,null",
                "cane sugar,null",
                "canola,null",
                "canola oil,null",
                "caprylic acid,null",
                "caramel,corn",
                "caramel color,corn",
                "caramel powder,corn",
                "carbon dioxide,sulphite",
                "carbonyl iron,null",
                "carbonyl iron (packaging),null",
                "carboxymethyl cellulose (packaging),null",
                "carnauba wax,null",
                "carob bean gum,null",
                "carotene (beta-carotene),null",
                "carrageenan,algae",
                "casein,milk",
                "cashew butter,nuts",
                "cashews,nuts",
                "celery powder,celery",
                "cellulose,null",
                "cellulose acetate(packaging),null",
                "cellulose gum,null",
                "cellulose powder,null",
                "chardonnay wine,null",
                "cheddar cheese (cultured milk,milk",
                "cheddar cheese (milk,milk",
                "cheddar cheese (pasteurized milk,milk",
                "cheese blend (mozzarella (pasteurized milk,milk",
                "cheese culture,milk",
                "cheese cultures,milk",
                "cheese cutlure,milk",
                "cheese made with skim milk (skim milk,milk",
                "cheese powder ,milk",
                "cherry puree concentrate,null",
                "chicken base [roasted chicken,chicken",
                "chicken breast flavor,chicken",
                "chicken breast strips (chicken breast meat with rib meat,chicken",
                "chicken fat,chicken",
                "chicken meat,chicken",
                "chicken protein,chicken",
                "chili pepper,null",
                "chocolate,milk",
                "chocolate extract,milk",
                "chocolate liquor,null",
                "cholic acid,beef",
                "choline bitartrate,null",
                "choline chloride,null",
                "chromium chloride,null",
                "chymosin preparation,null",
                "cider vinegar,null",
                "cinnamon,null",
                "cinnamon topping (sugar,null",
                "citric acid,null",
                "citric acid preservative,null",
                "citrus pectin,null",
                "clay (kaolin) (packaging),null",
                "clove bud extract,null",
                "clove bud oil,null",
                "clove bud oleoresin,null",
                "clove leaf oil,null",
                "clove stem oil,null",
                "cocoa,null",
                "cocoa (processed with al- kali),null",
                "cocoa (processed with a- kali),null",
                "cocoa butter,null",
                "cocoa processed with al- kali,null",
                "coconut,null",
                "coconut oil,null",
                "coconut oil (packaging),null",
                "coffee extract,null",
                "cold pressed coconut oil,null",
                "collagen casing,beef",
                "color added (including yellow 5 and yellow 6),null",
                "contains up to 10% water,null",
                "copper (cupric) gluconate,null",
                "copper (cupric) sulfate,null",
                "copper gluconate,null",
                "corn bran product,corn",
                "corn dextrins (packaging),corn",
                "corn oil,corn",
                "corn protein,corn",
                "corn silk,corn",
                "corn starch,corn",
                "corn starch concentrate,corn",
                "corn sugar (dextrose),corn",
                "corn syrup,corn",
                "corn syrup solids,corn",
                "cornstarch,corn",
                "cottonseed,null",
                "crackermeal [bleached wheat flour,gluten",
                "cream,milk",
                "crushed red peppers,null",
                "crust:  enriched wheat flour (flour,gluten",
                "cuprous iodide,null",
                "d- or dl- calcium pantothenate,null",
                "d- or dl- sodium pantothenate,null",
                "d(-)-lactic acid,null",
                "dark chicken,chicken",
                "dates,null",
                "degerminated yellow corn flour,corn",
                "dehydrated bell pepper,null",
                "dehydrated garlic,null",
                "dehydrated onion,null",
                "desoxycholic acid,null",
                "dextran,null",
                "dextrins,null",
                "dextrose,null",
                "diacetyl,null",
                "diatomaceous earth (filter aid),null",
                "dietary iron,null",
                "dilauryl thiodipropionate,null",
                "dipotassium phosphate,null",
                "disodium guanylate,null",
                "disodium inosinate,beef",
                "disodium inosinate and disodium guanylate,beef",
                "disodium inosinate and guanylate,beef",
                "disodium inosinate/disodium guanylate,beef",
                "disodium phosphate,null",
                "d-pantothenyl alcohol,null",
                "dried beef stock,beef",
                "dried blueberries,null",
                "dried cheese blend (gouda and edam cheeses [cultured milk,milk",
                "dried cranberries,null",
                "dried garlic,null",
                "dried onion,null",
                "dried onions,null",
                "dried red bell pepper,null",
                "dried whey,milk",
                "egg whites,eggs",
                "egg whties,eggs",
                "eggs,eggs",
                "enriched bleached wheat flour (enriched with niacin,gluten",
                "enriched bleached wheat flour (wheat flour,gluten",
                "enriched flour (wheat flour,gluten",
                "enriched flour (wheat flour bleached,gluten",
                "enriched pasta (semolina wheat flour,gluten",
                "enriched pasta [durum wheat semolina,gluten",
                "enriched wheat flour (niacin,gluten",
                "enriched wheat flour (wheat flour,gluten",
                "enzymatically hydrolyzed casein,milk",
                "filtered water,null",
                "fish oil, hydrogenated (packaging),fish",
                "flavor [maltodextrin,corn",
                "flavor blend (maltodextrin,corn",
                "flour,gluten",
                "fortification,null",
                "fructose,null",
                "fruit juice from concentrate (water,null",
                "garlic,null",
                "garlic and oil of garlic,null",
                "garlic powder,null",
                "gelatin,fish",
                "gellan gum,null",
                "glycerin,null",
                "glycerin and glycerides,null",
                "glycocholic acid,null",
                "glycyrrhiza,null",
                "green peppers,null",
                "grill flavor (maltodextrin,corn",
                "ground beef,beef",
                "ground celery seed,celery",
                "ground mustard,mustard",
                "ground vanilla beans,null",
                "guar gum,null",
                "gum arabic,null",
                "gum ghatti,null",
                "gum guaiac,null",
                "gum tragacanth,null",
                "helium gas,null",
                "herbs,null",
                "high amylose cornstarch,corn",
                "high fructose corn syrup,corn",
                "honey,honey",
                "hydrochloric acid,null",
                "hydrogen peroxide,null",
                "hydrogenated and/or partially hydrogenated palm kernel,null",
                "hydrogenated coconut oil,null",
                "hydrogenated soybean oil,soy",
                "hydrogenated tallow (packaging),null",
                "hydrolyzed corn,corn",
                "hydrolyzed corn protein,corn",
                "hydrolyzed soy,soy",
                "hydrolyzed soy protein,soy",
                "hydroxypropyl distarch glycerol,null",
                "hydroxypropyl distarch phosphate,null",
                "hyrolyzed soy protein,soy",
                "indian dill seed,null",
                "inositol,null",
                "invert cane syrup,null",
                "invert sugar,null",
                "isolated soy protein product [isolated soy protein,soy",
                "ketchup (tomato concentrate,null",
                "ketchup (tomato concentrate made form red ripe tomatoes,null",
                "ketchup (tomato concentrate made from red ripe tomatoes,null",
                "kibbled pumpkin seeds,null",
                "lactic acid powder (modified corn starch,corn",
                "lactose,milk",
                "lemon juice concentrate,null",
                "lemon juice powder (corn syrup solids,corn",
                "lemon juice solids (corn syrup solids,corn",
                "less than 2 percent soybean oil,soy",
                "less than 2% of: butter (cream,milk",
                "less than 2% of: cheeses (enzyme modified cheddar and pasteurized process cheddar [pasteurized milk,milk",
                "less than 2% of: salt,null",
                "less than 2% salt,null",
                "less than 2% salt mustard,mustard",
                "less than 2% soybean oil,soy",
                "lime juice concentrate,null",
                "lime juice powder (corn syrup solids,corn",
                "lime oil,null",
                "malt extract,gluten",
                "malt flavor,gluten",
                "maltodextrin,corn",
                "meatballs (pork,pork",
                "mechanically separated chicken,chicken",
                "milk protein concentrate,milk",
                "milkfat,milk",
                "modified corn starch,corn",
                "modified food starch and corn syrup solids,corn",
                "modified whey,milk",
                "modified whey cocoa (processed with alkali),milk",
                "monosodium glutamate,corn",
                "monosodium l-glutamate,corn",
                "mustard,mustard",
                "mustard and oil of mustard (brown and yellow),mustard",
                "mustard flour,mustard",
                "mustard seed,mustard",
                "natural and artificial flavor,null",
                "natural flavors (contains maltodextrin),corn",
                "natural flavors (includes hickory smoke flavor) beef extract,beef",
                "natural flavors including smoke (contains maltodextrin,corn",
                "natural grill flavor (maltodextrin,corn",
                "nonfat dried milk,milk",
                "nonfat dry milk,milk",
                "nonfat milk,milk",
                "nutmeg and mace,nuts",
                "oil of garlic,null",
                "olive oil/canola blend,null",
                "onion,null",
                "onion powder,null",
                "onions,null",
                "organic black pepper,null",
                "organic parsley,null",
                "organic pasta (organic durum wheat flour),gluten",
                "organic ricotta cheese,milk",
                "organic whole eggs,eggs",
                "palm,null",
                "palm and/or coconut oil,null",
                "palm kernel and palm oil,null",
                "palm oil,null",
                "paprika,null",
                "paprika extract color,null",
                "paprika for spice and coloring,null",
                "paprika oleoresin color,null",
                "parmesan cheese (cheese [pasteurized milk,milk",
                "parsley,null",
                "partially hydrogenated cottonseed and soybean oil,soy",
                "partially hydrogenated soybean and cottonseed oil,soy",
                "partially hydrogenated soybean and/or cottonseed oil,soy",
                "partially hydrogenated soybean oil,soy",
                "passion fruit juice concentrate*,null",
                "pasteurized processed cheddar cheese (cheddar cheese [cultured milk,milk",
                "pasteurized sweet cream,milk",
                "peach,null",
                "peanut butter (peanuts),peanuts",
                "peanut butter (roasted peanuts),peanuts",
                "peanut oil,peanuts",
                "peanut oil (packaging),peanuts",
                "peanuts,peanuts",
                "pear,null",
                "pineapple,null",
                "pineapple juice concentrate,null",
                "pineapple juice concentrate,null",
                "pistachios,nuts",
                "pork,pork",
                "potato extract,null",
                "potato flour,null",
                "potatoes,null",
                "predusted and battered with: water,null",
                "prepared mustard (water,mustard",
                "pumpkin seeds,null",
                "ravioli (organic flour,gluten",
                "red algae,algae",
                "red pepper,null",
                "red wine vinegar,null",
                "reduced iron,null",
                "reduced iron (packaging),null",
                "rennet,milk",
                "resistant corn starch,corn",
                "riboflavin,null",
                "riboflavin [vitamin b2],null",
                "riboflavin and folic acid,null",
                "riboflavin-5'-phosphate,null",
                "rice flour,null",
                "rice starch,null",
                "roasted almonds (almonds,nuts",
                "roasted rotisserie glaze (water,null",
                "roasted soybeans,soy",
                "rolled oats,null",
                "romano cheese (pasteurized cow's milk,milk",
                "romano cheese (pastuerized cow's milk,milk",
                "rosemary extract for freshness,null",
                "safflower,null",
                "safflower and/or sunflower oil,null",
                "salt,null",
                "salt hydrolyzed corn protein,corn",
                "savory glaze (water),null",
                "sea salt,null",
                "seasoning (dextrose),corn",
                "seasoning (maltodextrin),corn",
                "seasoning (salt),null",
                "seasoning (tomato powder,null",
                "seasoning [dextrose,corn",
                "seasoning [flavor (from corn oil,corn",
                "seasoning [soy sauce solids (soy beans,soy",
                "seasoning [spices,null",
                "semi sweet chocolate (sugar),null",
                "semisweet chocolate (chocolate,null",
                "semisweet chocolate chips (dried cane syrup),corn",
                "semi-sweet chocolate chunks,null",
                "semisweet chocolate chunks (dried cane syrup,corn",
                "semisweet chocolate chunks (sugar,null",
                "sesame oil,sesame",
                "sesame seeds,sesame",
                "silica aerogel,null",
                "silicon dioxide,sulphite",
                "silicon dioxides,sulphite",
                "skim milk,milk",
                "sodium,null",
                "sodium acetate,null",
                "sodium caseinate,milk",
                "sodium phosphate and chicken protein,chicken",
                "soluble corn fiber,corn",
                "sorbic acid,null",
                "sorbic acid as a preservative,null",
                "sorbitan monostearate,null",
                "sorbitol,corn",
                "sorbose (packaging),null",
                "sorghum,null",
                "sour cream (cultured pasteurized light cream,milk",
                "soy,soy",
                "soy flour,soy",
                "soy lecithin,soy",
                "soy protein concentrate,soy",
                "soy protein isolate,soy",
                "soy sauce,soy",
                "soy sauce solids (soybeans,soy",
                "soy sauces,soy",
                "soybean,soy",
                "soybean and corn extracts,soy",
                "soybean and palm oil with tbhq for freshness,soy",
                "soybean and/or canola oil,soy",
                "soybean and/or sunflower oil,soy",
                "soybean oil,soy",
                "soybean oil with tbhq for freshness,soy",
                "soybean or canola oil,soy",
                "soybeans,soy",
                "spice,null",
                "spice extractive,null",
                "spice extractives,null",
                "spices,null",
                "spinach,null",
                "stannous chloride,null",
                "starch,corn",
                "sugar,null",
                "tahini (ground sesame),sesame",
                "teriyaki glaze (water,soy",
                "teriyaki sauce ingredients: soy sauce (water,soy",
                "textured soy flour,soy",
                "textured soy flour (soy flour,soy",
                "textured vegetable protein (soy flour,soy",
                "textured vegetable protein (soy flour),soy",
                "textured vegetable protein (soy protein concentrate,soy",
                "textured vegetable protein product (soy flour,soy",
                "toasted soy pieces,soy",
                "tomato paste,null",
                "tomato powder,null",
                "tomato puree (water,null",
                "tomatoes (tomato puree,null",
                "vanilla extract,corn",
                "vegetable oil (canola and/or soy),soy",
                "vegetable oil (corn,corn",
                "vegetable oil (soybean,soy",
                "vegetable protein product (isolated soy protein,soy",
                "vegetable seasoning blend (potato extract,soy",
                "vinegar,null",
                "vinegar powder,null",
                "vital wheat gluten,gluten",
                "vitamin b12,null",
                "vitamin b12 (cyanocobalamin),null",
                "vitamin d,null",
                "vitamin d2 (ergocalciferol),null",
                "vitamin d3,null",
                "vitamin d3 (cholecalciferol),null",
                "water,null",
                "waxy maize starch,corn",
                "wheat,gluten",
                "wheat berries,gluten",
                "wheat corn,corn",
                "wheat flour,gluten",
                "wheat gluten,gluten",
                "wheat starch,gluten",
                "whey,milk",
                "whey cream,milk",
                "whey protein,milk",
                "whey protein concentrate,milk",
                "white cheddar cheese (pasteurized milk,milk",
                "white pepper,null",
                "white vinegar,null",
                "whole grain oat flour,null",
                "whole grain oats,null",
                "whole wheat,gluten",
                "whole wheat flour,gluten",
                "xanthan gum,corn",
                "xanthum gum,corn",
                "yellow corn flour,corn",
                "1% milk,milk",
                "2% milk,milk",
                "acid whey,milk",
                "acidophilus milk,milk",
                "alanine,null",
                "albumen,eggs",
                "albumin,eggs",
                "aldioxa,null",
                "aliphatic alcohol,null",
                "all purpose flour,gluten",
                "allantoin,null",
                "allantoin,null",
                "almond paste,nuts",
                "alpha tocopherol acetate (vitamin e),null",
                "ambergris,null",
                "amerchol l101,null",
                "amino acids,null",
                "ammonium caseinate,milk",
                "angus beef,beef",
                "anhydrous milk fat,milk",
                "apples,null",
                "arachic oil,peanuts",
                "arachis,peanuts",
                "arachis hypogaea,peanuts",
                "artificial butter,milk",
                "artificial butter flavor,milk",
                "artificial nuts,peanuts",
                "asafoetida,null",
                "atta flour,gluten",
                "baking soda,null",
                "barnacle,shellfish",
                "bean curd,soy",
                "beechnut,nuts",
                "beef,beef",
                "beef,beef",
                "beef chuck tender,beef",
                "beef contains up to 15% solution of water,beef",
                "beef liver,beef",
                "beer nuts,peanuts",
                "bengal gram,null",
                "benne,sesame",
                "benne seed,sesame",
                "benniseed,sesame",
                "bht (preservative),null",
                "bht to preserve freshness,null",
                "black gram,null",
                "blackberries,null",
                "blackberry flavour,null",
                "blueberries,null",
                "boiled peanut,peanuts",
                "brazil nut,nuts",
                "bread crumbs,gluten",
                "bread flour,gluten",
                "bromated flour,gluten",
                "brown sugar,null",
                "bulgur,gluten",
                "bush nut,nuts",
                "butter extract,milk",
                "butter fat,milk",
                "butter flavored oil,milk",
                "butter solids,milk",
                "buttermilk,milk",
                "buttermilk blend,milk",
                "buttermilk solids,milk",
                "butternut,nuts",
                "cake flour,gluten",
                "calcium,null",
                "calcium carbonate,null",
                "calcium caseinate,milk",
                "calcium hydrogen sulfite (preservative),sulphite",
                "calcium hydrogen sulphite,sulphite",
                "calcium sulfite,sulphite",
                "calcium sulphite,sulphite",
                "capryl betaine,null",
                "caprylamine oxide,null",
                "caprylic triglyceride,null",
                "caramel colorand maltodextrin,corn",
                "carmine,insects",
                "casein hydrolysate,milk",
                "cashew,nuts",
                "cashews,nuts",
                "cassia,null",
                "caustic sulphite caramel,sulphite",
                "cereal extract,gluten",
                "certified angus beef,beef",
                "cherries,null",
                "chestnut,nuts",
                "chicken (mechanically separated),chicken",
                "chicken breast,chicken",
                "chicken breast fillets containing up to 10% of a solution of water,chicken",
                "chicken breast fillets containing up to 15% of a solution of water,chicken",
                "chicken breast meat,chicken",
                "chicken breast with rib meat,chicken",
                "chilli,null",
                "cholesterol free egg substitute,eggs",
                "club,gluten",
                "cocoa,null",
                "cocoa (processed with allt kali),null",
                "cold pressed peanut oil ,peanuts",
                "common flour,gluten",
                "common salt,null",
                "concentrated beef stock,beef",
                "condensed milk,milk",
                "contains 2% or less of whey,milk",
                "contains up to 10% water,null",
                "contains up to 20 percent solution of water,null",
                "cooked pasta (water,gluten",
                "coriander,null",
                "corn,corn",
                "corn flour,corn",
                "corn syrup,corn",
                "couscous,gluten",
                "crab,shellfish",
                "cracker meal,gluten",
                "cracker: enriched flour (wheat flour,gluten",
                "crawfish,shellfish",
                "crayfish,shellfish",
                "crushed nuts,peanuts",
                "crushed peanuts ,peanuts",
                "cultured milk,milk",
                "cumin,null",
                "cured whey,milk",
                "curry leaf,null",
                "custard,milk",
                "d2,null",
                "d3,fish",
                "dairy butter,milk",
                "degermed corn meal,corn",
                "degerminated yellow corn flour,corn",
                "degerminated yellow corn meal,corn",
                "delactosed whey,milk",
                "demineralized whey,milk",
                "dried egg,eggs",
                "dried egg solids,eggs",
                "dried milk,milk",
                "dried potatoes,null",
                "dry milk solids (dms),milk",
                "dry roasted peanuts,peanuts",
                "durum flour,gluten",
                "e150d,sulphite",
                "e220,sulphite",
                "e221,sulphite",
                "e222,sulphite",
                "e223,sulphite",
                "e224,sulphite",
                "e226,sulphite",
                "e227,sulphite",
                "e228,sulphite",
                "e414,null",
                "earth nuts,peanuts",
                "edamame,soy",
                "egg,eggs",
                "egg wash,eggs",
                "egg white,eggs",
                "egg yolk,eggs",
                "eggnog,eggs",
                "einkorn,gluten",
                "einkorn flour,gluten",
                "elderberries,null",
                "emmer,gluten",
                "emmer flour,gluten",
                "enhanced with up to a 12% solution of water,null",
                "enhanced with up to a 12% solution of: water,null",
                "enriched  flour,gluten",
                "enriched flour (wheat flour,gluten",
                "enrichment,null",
                "evaporated milk,milk",
                "expelled peanut oil ,peanuts",
                "extruded peanut oil ,peanuts",
                "farina,gluten",
                "farina flour,gluten",
                "farro,gluten",
                "fat substitutes,eggs",
                "fat?free milk,milk",
                "fenugreek,null",
                "filbert,nuts",
                "fish flavoring,fish",
                "fish flour,fish",
                "fish fume,fish",
                "fish oil,shellfish",
                "flatbread crisps: wheat flour,gluten",
                "folic acid,null",
                "fu,gluten",
                "fully cream milk powder,milk",
                "galactose,milk",
                "gelatin,fish",
                "ghee,milk",
                "gianduja,nuts",
                "gingelly,sesame",
                "gingelly oil,sesame",
                "ginkgo nut,nuts",
                "ginko nut,nuts",
                "globulin,eggs",
                "goat’s milk,milk",
                "goat’s milk,milk",
                "gomasio,sesame",
                "goober peas,peanuts",
                "goobers,peanuts",
                "graham flour,gluten",
                "ground beef,beef",
                "ground beef (not more than 20% fat),beef",
                "ground nuts,peanuts",
                "ground peanuts ,peanuts",
                "ground pork,pork",
                "half & half,milk",
                "halvah,sesame",
                "hazelnut,nuts",
                "hibiscus flowers,null",
                "hickory nut,nuts",
                "high fructose corn syrup,corn",
                "high gluten flour,gluten",
                "high protein flour,gluten",
                "hominy,corn",
                "hydrolysate,milk",
                "hydrolyzed casein,milk",
                "hydrolyzed peanut protein,peanuts",
                "hydrolyzed soy protein,soy",
                "hydrolyzed whey,milk",
                "hypogaeic acid,peanuts",
                "imitation sour cream,milk",
                "instant pastry flour,gluten",
                "iron caseinate,milk",
                "kamut,gluten",
                "kamut flour,gluten",
                "khorasan wheat,gluten",
                "kinnoko flour,soy",
                "krill,shellfish",
                "kyodofu,soy",
                "lactaid® milk,milk",
                "lactalbumin,milk",
                "lactalbumin phosphate,milk",
                "lactate solids,milk",
                "lactitol monohydrate,milk",
                "lactoglobulin,milk",
                "lactose free milk,milk",
                "lactulose,milk",
                "lactyc yeast,milk",
                "langouste,shellfish",
                "langoustine,shellfish",
                "leavening (baking soda and/ or calcium phosphate),null",
                "lemon juice concentrate,null",
                "lichee,nuts",
                "lichee nut,nuts",
                "litchi,nuts",
                "livetin,eggs",
                "lobster,shellfish",
                "low fat milk,milk",
                "lupin bean,lupin",
                "lupin flour,lupin",
                "lupin seed,lupin",
                "lupine,lupin",
                "lychee nut,nuts",
                "lysozyme,eggs",
                "macadamia nut,nuts",
                "magnesium caseinate,milk",
                "maida flour,gluten",
                "malt,gluten",
                "malt extract,gluten",
                "malted barley extract,gluten",
                "malted milk,milk",
                "maltodextrin,corn",
                "maltodextrin,corn",
                "mandelonas,peanuts",
                "marzipan,nuts",
                "matzo,gluten",
                "mayonnaise,eggs",
                "meringue,eggs",
                "meringue powder,eggs",
                "microcrystalline,null",
                "milk,milk",
                "milk derivative,milk",
                "milk fat,milk",
                "milk powder,milk",
                "milk protein,milk",
                "milk protein,milk",
                "milk protein concentrate,milk",
                "milk solid pastes,milk",
                "milk solids,milk",
                "miso,soy",
                "mixed nuts,peanuts",
                "modified whey,milk",
                "molasses,null",
                "monkey nuts,peanuts",
                "moreton bay bugs,shellfish",
                "nangai nut,nuts",
                "natto,soy",
                "natural butter,milk",
                "natural butter flavor,milk",
                "natural chicken juices,chicken",
                "natural flavor,null",
                "natural nut extract,nuts",
                "niacinamide (vitamin b3),null",
                "nisin preparation,milk",
                "nonfat dry milk,milk",
                "nonfat milk,milk",
                "nonfat milk,milk",
                "nonfat milk solids,milk",
                "noodles,gluten",
                "nougat,milk",
                "nu nuts flavored nuts,peanuts",
                "nut meal,nuts",
                "nut meat,nuts",
                "nut paste,nuts",
                "nut pieces,peanuts",
                "nut pieces,nuts",
                "nutmeat,peanuts",
                "okara,soy",
                "orange ingredients: water,null",
                "orange peels,null",
                "ovalbumin,eggs",
                "ovoglobulin,eggs",
                "ovomucin,eggs",
                "ovomucoid,eggs",
                "ovotransferrin,eggs",
                "ovovitelia ,eggs",
                "ovovitellin,eggs",
                "pasta,gluten",
                "pasteurized milk,milk",
                "peanut butter,peanuts",
                "peanut butter chips,peanuts",
                "peanut butter morsels,peanuts",
                "peanut flour,peanuts",
                "peanut paste,peanuts",
                "peanut syru,peanuts",
                "peanuts,peanuts",
                "peanuts,peanuts",
                "peanuts sauce,peanuts",
                "pecan,nuts",
                "peppermint,null",
                "pesto,nuts",
                "phosphated flour,gluten",
                "pigeon pea (toovar dal/ahvar dal),null",
                "pili nut,nuts",
                "pine nut,nuts",
                "pistachio,nuts",
                "pita crackers: wheat flour,gluten",
                "plain flour,gluten",
                "polenta,corn",
                "pork,pork",
                "pork liver,pork",
                "pork loin,pork",
                "potassium caseinate,milk",
                "potassium hydrogen sulphite,sulphite",
                "potassium metabisulphite,sulphite",
                "powdered eggs ,eggs",
                "powdered milk,milk",
                "powdered whey,milk",
                "praline,nuts",
                "prawns,shellfish",
                "protein hydrolysate,milk",
                "pudding,milk",
                "pyridoxine hydrochloride (vitamin b6),null",
                "quark,milk",
                "raspberries,null",
                "raspberry flavour,null",
                "recaldent,milk",
                "reduced iron,null",
                "reduced mineral whey,milk",
                "rennet casein,milk",
                "riboflavin (vitamin b2),null",
                "rice,null",
                "rice flour,null",
                "roasted almonds,nuts",
                "roasted beef and concentrated beef stock,beef",
                "rosehip,null",
                "salt,null",
                "salt,null",
                "sauce (water,null",
                "scampi,shellfish",
                "sea salt,null",
                "seitan,gluten",
                "self-rising flour,gluten",
                "semolina,gluten",
                "semolina flour,gluten",
                "sesame flour,sesame",
                "sesame oil,sesame",
                "sesame paste,sesame",
                "sesame salt,sesame",
                "sesame seed,sesame",
                "sesamol,sesame",
                "sesamum indicum,sesame",
                "shea nut,nuts",
                "sheep’s milk,milk",
                "shellac,insects",
                "shoyu sauce,soy",
                "shrimp crevette,shellfish",
                "shrimp scampi,shellfish",
                "silici albuminate,eggs",
                "sim sim,sesame",
                "simplesse,eggs",
                "simplesse,milk",
                "skim milk,milk",
                "skim milk powder,milk",
                "sodium ascorbate,null",
                "sodium bicarbonate,null",
                "sodium caseinate,milk",
                "sodium hydrogen sulphite,sulphite",
                "sodium metabisulphite,sulphite",
                "sodium sulphite,sulphite",
                "soft wheat flour,gluten",
                "sour cream,milk",
                "sour cream solids,milk",
                "sour milk,milk",
                "sour milk solids,milk",
                "soy albumin,soy",
                "soy bran,soy",
                "soy concentrate,soy",
                "soy fiber,soy",
                "soy flour,soy",
                "soy formula,soy",
                "soy grits,soy",
                "soy lecithin,soy",
                "soy milk,soy",
                "soy miso,soy",
                "soy nut butter,soy",
                "soy nuts,soy",
                "soy protein,soy",
                "soy protein concentrate,soy",
                "soy protein isolat,soy",
                "soy protein isolate,soy",
                "soy sauce,soy",
                "soy sprouts,soy",
                "soya,soy",
                "soya flour,soy",
                "soybean curd,soy",
                "soybean flour,soy",
                "soybean granules,soy",
                "soybean oil,soy",
                "soybean paste,soy",
                "soybeans,soy",
                "spanish peanuts,peanuts",
                "spelt flour,gluten",
                "spelt flour,gluten",
                "sprouted wheat,gluten",
                "steel ground flour,gluten",
                "stone ground flour,gluten",
                "strawberries,null",
                "strawberry ingredients: water,null",
                "sugar,null",
                "sugar,null",
                "sulphite ammonia caramel,sulphite",
                "sulphur dioxide,sulphite",
                "supro,soy",
                "surimi,eggs",
                "sweet cream buttermilk powder,milk",
                "sweetened condensed milk,milk",
                "sweetened condensed skim milk,milk",
                "sweetened condensed skim milk,milk",
                "tabbouleh,gluten",
                "tahina,sesame",
                "tahini,sesame",
                "tamales,corn",
                "tamari,soy",
                "tamarind,null",
                "tehina,sesame",
                "tempeh,soy",
                "teriyaki sauce,soy",
                "textured soy flour (tsf),soy",
                "textured soy protein (tsp),soy",
                "textured vegetable protein (tvp),soy",
                "thiamin mononitrate (vitamin b1),null",
                "til,sesame",
                "titanium dioxide (color),null",
                "tofu,soy",
                "tomalley,shellfish",
                "trailblazer,eggs",
                "triticale,gluten",
                "triticale flour,gluten",
                "triticum,gluten",
                "triticum flour,gluten",
                "turmeric,null",
                "unbleached enriched flour (wheat flour, niacin, re- duced iron, thiamin mononitrate (vitamin : b1), riboflavin (vitamin b2), folic acid),gluten",
                "unbleached flour,gluten",
                "virginia peanuts,peanuts",
                "vital gluten,gluten",
                "vital wheat gluten,gluten",
                "vitamin a palmitate,null",
                "vitellin,eggs",
                "walnut,nuts",
                "water,null",
                "wheat bran,gluten",
                "wheat flour,gluten",
                "wheat flour bread,gluten",
                "wheat germ,gluten",
                "wheat germ oil,gluten",
                "wheat gluten,gluten",
                "wheat protein,gluten",
                "wheat protein isolate,gluten",
                "wheat sprouts,gluten",
                "wheat starch,gluten",
                "wheatgrass,gluten",
                "whey,milk",
                "whey hydrolysate,milk",
                "whey powder,milk",
                "whey protein,milk",
                "whey protein concentrate,milk",
                "whey protein hydrolysate,milk",
                "whey solids,milk",
                "whipped butter,milk",
                "white flour,gluten",
                "white flour bread,gluten",
                "whole egg,eggs",
                "whole grain oats,null",
                "whole grain rolled oats,null",
                "whole milk,milk",
                "whole wheat bread,gluten",
                "whole wheat flour,gluten",
                "whole wheat flour,gluten",
                "whole wheat flour,gluten",
                "yakidofu,soy",
                "yellow 5,null",
                "yellow 6,null",
                "yogurt,milk",
                "yogurt powder,milk",
                "yuba,soy",
                "zea mays,corn",
                "zein,corn",
                "zinc caseinate,milk",
                "zinc oxide,null",
                "niacin,null",
                "re- duced iron,null",
                "folic acid),null",
                "titaniunm diox- ide (color),null",
                "high fructose/or calcium phosphate),null",
                "corn starch (contains sulfite),sulphite",
                "vegetable oil, null",
                "flavourings,null"};

        db.execSQL("delete from " + ING_TABLE);

        for(int i=0;i<s.length;i++)
        {
            String [] sp = s[i].split(",");
            contentValues.put(INGREDIENT,sp[0]);
            contentValues.put(FAMILY,sp[1]);

            db.insert(ING_TABLE,null ,contentValues);
        }
    }

    //get information about the family in user profile (eating habits)
    public Cursor getIngredientFromDb(String ingredient) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from ingredientsTable where ingredient='"+ingredient+"'",null);

        return res;
    }

    //initialize user choices statistics
    public Integer getRateFromProfile(String family){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select profileID from usersTable where isActive=1", null);//find the active user profileID
        res.moveToFirst();
        int data = res.getInt(0);

        Cursor rate = db.rawQuery("select * from profiles_table where profileID="+data+"", null);

        Integer index=rate.getColumnIndex(family);
        rate.moveToFirst();
        Integer ingredientRate=rate.getInt(index);

        return ingredientRate;
    }

    //insert rows for new user in the recovery table
    public void insertRowsForNewUserInRecovery() {
        SQLiteDatabase db = this.getWritableDatabase();

        int pid = getActivateUserProfileId();

        ContentValues contentValues = new ContentValues();
        contentValues.put(R_PROFILEID, pid);
        contentValues.put(IS_NEVER, 0);
        contentValues.put(R_BEEF, 0);
        contentValues.put(R_CHICKEN, 0);
        contentValues.put(R_PORK, 0);
        contentValues.put(R_FISH, 0);
        contentValues.put(R_INSECTS, 0);
        contentValues.put(R_EGGS, 0);
        contentValues.put(R_MILK, 0);
        contentValues.put(R_HONEY, 0);
        contentValues.put(R_GLUTEN, 0);
        contentValues.put(R_LUPIN, 0);
        contentValues.put(R_SESAME, 0);
        contentValues.put(R_ALGAE, 0);
        contentValues.put(R_SHELLFISH, 0);
        contentValues.put(R_SOY, 0);
        contentValues.put(R_PEANUTS, 0);
        contentValues.put(R_SULPHITE, 0);
        contentValues.put(R_NUTS, 0);
        contentValues.put(R_MUSTARD, 0);
        contentValues.put(R_CELERY, 0);
        contentValues.put(R_CORN, 0);

        db.insert(RECOVERY_TABLE, null, contentValues); //occ row

        contentValues.put(IS_NEVER, 1);
        db.insert(RECOVERY_TABLE, null, contentValues); //never row
    }

    //get the active user profile ID
    public int getActivateUserProfileId()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select profileID from usersTable where isActive=1", null);//find the active user profileID
        if(res.getCount()==0)
            return -1;
        res.moveToFirst();
        return res.getInt(0);
    }

    //check if exist information in Recovery Table
    public boolean checkIfExistInRecoveryTable()
    {
        int pId = getActivateUserProfileId();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select profileID from " +RECOVERY_TABLE+" where "+R_PROFILEID+"="+pId, null);
        if(res.getCount()==0)
            return false;
        return true;
    }

    //check if got to 10 ingredients that classified in "never families"
    public String checkIfGotTo10NeverIng()
    {
        int pId = getActivateUserProfileId();
        String str = "";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " +RECOVERY_TABLE+" where isNever=1"+" and profileID="+pId, null);
        res.moveToFirst();

        for(int i=2;i<22;i++)
        {
            int data = res.getInt(i);
            if(data>=10)
            {
                switch(i)
                {
                    case 2:
                        str = str + "Beef\n";
                        break;
                    case 3:
                        str = str + "Chicken\n";
                        break;
                    case 4:
                        str = str + "Pork\n";
                        break;
                    case 5:
                        str = str + "Fish\n";
                        break;
                    case 6:
                        str = str + "Insects\n";
                        break;
                    case 7:
                        str = str + "Eggs\n";
                        break;
                    case 8:
                        str = str + "Milk\n";
                        break;
                    case 9:
                        str = str + "Honey\n";
                        break;
                    case 10:
                        str = str + "Gluten\n";
                        break;
                    case 11:
                        str = str + "Lupin\n";
                        break;
                    case 12:
                        str = str + "Sesame\n";
                        break;
                    case 13:
                        str = str + "Algae\n";
                        break;
                    case 14:
                        str = str + "Shellfish\n";
                        break;
                    case 15:
                        str = str + "Soy\n";
                        break;
                    case 16:
                        str = str + "Peanuts\n";
                        break;
                    case 17:
                        str = str + "Sulphite\n";
                        break;
                    case 18:
                        str = str + "Nuts\n";
                        break;
                    case 19:
                        str = str + "Mustard\n";
                        break;
                    case 20:
                        str = str + "Celery\n";
                        break;
                    case 21:
                        str = str + "Corn\n";
                        break;
                    default:

                }

                ContentValues contentValues = new ContentValues();
                contentValues.put(res.getColumnName(i),0);

                db.update(RECOVERY_TABLE,contentValues,"isNever=1 and profileID="+pId,null);
            }
        }

        return str;
    }

    //check if got to 10 ingredients that classified in "occationaly families"
    public String checkIfGotTo10OccIng()
    {
        int pId = getActivateUserProfileId();
        String str = "";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " +RECOVERY_TABLE+" where isNever=0"+" and profileID="+pId, null);
        res.moveToFirst();

        for(int i=2;i<22;i++)
        {
            int data = res.getInt(i);
            if(data>=10)
            {
                switch(i)
                {
                    case 2:
                        str = str + "Beef\n";
                        break;
                    case 3:
                        str = str + "Chicken\n";
                        break;
                    case 4:
                        str = str + "Pork\n";
                        break;
                    case 5:
                        str = str + "Fish\n";
                        break;
                    case 6:
                        str = str + "Insects\n";
                        break;
                    case 7:
                        str = str + "Eggs\n";
                        break;
                    case 8:
                        str = str + "Milk\n";
                        break;
                    case 9:
                        str = str + "Honey\n";
                        break;
                    case 10:
                        str = str + "Gluten\n";
                        break;
                    case 11:
                        str = str + "Lupin\n";
                        break;
                    case 12:
                        str = str + "Sesame\n";
                        break;
                    case 13:
                        str = str + "Algae\n";
                        break;
                    case 14:
                        str = str + "Shellfish\n";
                        break;
                    case 15:
                        str = str + "Soy\n";
                        break;
                    case 16:
                        str = str + "Peanuts\n";
                        break;
                    case 17:
                        str = str + "Sulphite\n";
                        break;
                    case 18:
                        str = str + "Nuts\n";
                        break;
                    case 19:
                        str = str + "Mustard\n";
                        break;
                    case 20:
                        str = str + "Celery\n";
                        break;
                    case 21:
                        str = str + "Corn\n";
                        break;
                    default:

                }

                ContentValues contentValues = new ContentValues();
                contentValues.put(res.getColumnName(i),0);

                db.update(RECOVERY_TABLE,contentValues,"isNever=0 and profileID="+pId,null);
            }
        }

        return str;
    }

    //add +1 to the right family category after ingredients search (never/occationaly)
    public void updateRelevantFamily(String family,int isNever)
    {
        int pId = getActivateUserProfileId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select "+family+" from " +RECOVERY_TABLE+" where isNever="+isNever+" and profileID="+pId, null);
        res.moveToFirst();
        int data = res.getInt(0);

        ContentValues contentValues = new ContentValues();
        contentValues.put(family,data+1);

        db.update(RECOVERY_TABLE,contentValues,"isNever="+isNever+" and profileID="+pId,null);

        return;
    }

    //check if exist profile id for the activated user
    public boolean checkIfExistProfileIdForUser(String uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1 = db.rawQuery("select profileID from " +USERS_TABLE+" where userId='"+uid+"'", null);
        res1.moveToFirst();

        Cursor res2 = db.rawQuery("select * from " +PROFILES_TABLE+" where profileID="+res1.getInt(0), null);

        if(res2.getCount()!=0)
            return true;
        return false;
    }

    //check if null user so delete (didn't choose pid)
    public void checkIfNullUser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +USERS_TABLE+" where profileID=null", null);

        if(res.getCount()!=0) //there is a null user
        {
            db.delete(USERS_TABLE, "profileID=null", null);
        }
    }


}
