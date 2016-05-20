package com.example.admin.calandburn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 10/11/2558.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String DATABASE_NAME = "my_calandburn.db";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE = "create table userTABLE (_id integer primary key, " +
            "date text, " +
            "name text, " +
            "sex text, " +
            "age text, " +
            "height double, " +
            "weight double, " +
            "myact text, " +
            "factor text, " +
            "bmiuser double, " +
            "bmruser double);";

    private static final String FOOD_TABLE = "create table foodTABLE (_id integer primary key, " +
            "namefood text, " +
            "calfood text, " +
            "aboutfood text);";

    // join table food
    //private static final String TypeFOOD_TABLE = "create table typeFoodTable (_idTypeFood integer primary key,";

    private static final String ACTIVITY_TABLE = "create table activityTABLE (_id integer primary key, " +
            "nameact text, " +
            "burnact text, " +
            "aboutact text);";

    private static final String calary_table = "create table calary_table (" +
            "_id integer primary key, " +
            "Date text, " +
            "Food text, " +
            "Amount text," +
            "CalFood text);";

    private static final String burn_table = "create table burn_table (" +
            "_id integer primary key, " +
            "Date text, " +
            "Exercise text, " +
            "Hour text, " +
            "CalBurn text);";

    public MyOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    } //Construstor

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(calary_table);
        db.execSQL(burn_table);
        db.execSQL(USER_TABLE);
        db.execSQL(FOOD_TABLE);
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('กระเพาะปลา 1 ชาม(392 กรัม)', 239.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ก๋วยจั๊บน้ำใส 1 ชาม(347 กรัม)', 260.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ก๋วยจั๊บน้ำข้น 1 ชาม(347 กรัม)', 280.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('เส้นใหญ่เยนตาโฟ 1 ชาม(494 กรัม)', 352.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('บะหมี่ต้มย้ำ 1 ชาม(420 กรัม)', 310.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ผัดไทยกุ้งสด 1 จาน(281 กรัม)', 486.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ราดหน้าหมู 1 จาน(354 กรัม)', 397.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('เส้นหมี่เนื้อน้ำตก 1 ชาม(364 กรัม)', 233.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ขนมจีนน้ำยา 1 จาน(435 กรัม)', 332.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ขนมจีนน้ำเงี้ยว 1 จาน(323 กรัม)', 243.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวมันไก่ 1 จาน(300 กรัม)', 596.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวขาหมู 1 จาน(289 กรัม)', 438.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวคลุกกะปิ 1 จาน(296 กรัม)', 641.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวซอยไก่, หมู', 395.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวต้มกุ้ง 1 ชาม(280 กรัม)', 305.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวผัดกระเพราไก่ 1 จาน(265 กรัม)', 478.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวคะน้าหมูกรอบ 1 จาน(270 กรัม)', 620.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวหมูแดง 1 จาน(320 กรัม)', 537.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('โจ๊กหมู 1 ชาม(415 กรัม)', 253.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวสวย 1 ทัพพี', 60.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ไข่เจียว 1 ฟอง(60 กรัม)', 253.00, '1');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ไข่ดาว 1 ฟอง(53 กรัม)', 125.00, '1');");

        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('กล้วยบวชชี 1 ถ้วย(166 กรัม)', 255.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ฟักทองแกงบวด 1 ถ้วย(162 กรัม)', 359.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ขนมครก 1 คู่(22 กรัม)', 97.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('เผือกน้ำกระทิ 1 ถ้วย(163 กรัม)', 256.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ข้าวต้มมัด 1 มัด', 285.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ทับทิมกรอบ 1 ถ้วย(185 กรัม)', 276.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('สลิ่ม 1 ถ้วย(160 กรัม)', 217.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ลอดช่องน้ำกระทิ 1 ถ้วย(111 กรัม)', 167.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('เอแคร์ไส้ครีม 1 ชิ้น', 225.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ไอศกรีมกาแฟ 1 ลูก', 142.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ไอศกรีมชอกโกแล็ต 1 ลูก', 110.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ไอศกรีมวนิลา 1 ลูก', 140.00, '2');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ไอศกรีมสตรอเบอรี่ 1 ลูก', 110.00, '2');");

        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('น้ำมะนาว 1 แก้ว(355 กรัม)', 200.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('นมจืด สูตรปกติ (200 มล.)', 130.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('นมจืด ไขมัน0% (200 มล.)', 70.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('น้ำส้มคั้นมาลี 100% (200 มล.)', 100.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('น้ำส้มเขียวหวานมาลี 100% (200 มล.)', 100.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('น้ำผักรวมทิปโก้ 100% (200 มล.)', 100.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('นมเปรี้ยว ดัชมิลล์ (200 มล.)', 1700.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('วอดก้า 60 cc', 120.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('วิสกี้ 60 cc', 140.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('น้ำอัดลมเอส (200 มล.)', 90.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('โค้ก 1 ขวดเล็ก(290 มล.)', 174.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('โอเลี้ยง', 165.00, '3');");
        db.execSQL("INSERT INTO FoodTABLE(namefood, calfood, aboutfood) VALUES ('ไวน์แชมเปญ 60 cc', 42.00, '3');");

        db.execSQL(ACTIVITY_TABLE);
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('กระโดดเชือก', 780.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('กวาดพื้น', 225.00, '2');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('ขี่จักรยาน', 450.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('มวยไทย', 800.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('วิ่งบนทางราบ 12.8กม./ชม.', 825.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('วิ่งบนทางราบ 18.2กม./ชม.', 1390.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เดินช้า', 150.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เดินธรรมดา', 300.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เลื่อยไม้', 515.00, '2');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เล่นวอลเล่ย์บอล', 300.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เล่นฮูล่าฮูป', 430.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เล่นเทนนิสคู่', 780.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เล่นเทนนิสเดี่ยว', 780.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เล่นแบดมินตัน', 780.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('แอโรบิค', 780.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เล่นโบว์ลิ่ง', 400.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เดินลงบันได', 425.00, '1');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('ซักผ้าด้วยมือ', 240.00, '2');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('นอนหลับ', 75.00, '2');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('นั่งดูทีวี', 100.00, '2');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('นั่งทำงานใช้สมอง', 110.00, '2');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('ยืน', 140.00, '2');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('รีดผ้า', 150.00, '2');");
        db.execSQL("INSERT INTO activityTABLE( nameact, burnact, aboutact) VALUES ('เย็บผ้า', 115.00, '2');");


    } //onCreate สร้างฐานข้อมูล

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    } //onUpgrade อัพเกรดฐานข้อมูล
} //main class
