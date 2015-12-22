package com.andrey.kostin.calc;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
//import android.net.Uri;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;


public class MainActivity extends Activity implements View.OnClickListener{
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bsign,bdot,bresult,bplus,bminus,bdiv,bmult, bpi,broot,bstepen,bdrob,bc,bce,bdel,bMplus,bMminus,bMC,bMR;
    TextView okno;
    String stroka="",btouch="";   //переменная для вывода чисел в окно и переменная для внутренних вычислениях при мат операциях
    double num1=0.0, num2=0.0, numtemp=0.0,mem=0.0; //переменные для математических операций, mem для работы с М+ М-
    Boolean numclick=false;//переменная определяющая набрана цифра - истина или нажата мат функция -ложь
    Toast mytoast;  //переменная для вывода всплывающих сообщений
    BigDecimal okrugl; //переменная для округления результатов до 6 знаков после запятой

    Typeface crystalfont;//переменная для задания стиля шрифта

    Integer i; //переменная для циклов

    private final static String TAG="mylog"; //константа для ведения логов

    DisplayMetrics myDisplay; //переменная параметров дисплея
    Integer  sizeY=0,textheight, dlinaChisla=14; //переменные для указания размеров высоты пропорционально дисплея и высоты текста
    Intent intent;          //    Intent settingsIntent; //переменная интент для вызова настроек

    SharedPreferences sp;   //переменная для обращения к хранимым настройкам приложения
    Integer floatnumber;           //переменная для передачи числа знаков после точки из настроек
    String fontstyle;           //переменная для передачи имени стиля шрифта из настроек
    String memstroka;           //переменная для передачи последнего результата вычислений в настройки для последующего извлечения при старте



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        sp= PreferenceManager.getDefaultSharedPreferences(this);//получаем ШаредПреференсес которое работает с файлом настроек
        //sp.edit().clear().commit();//команда очистки настроек (пока мне не нужна)






     //запуск плеймаркета если он установлен или нет
/*            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.andrey.kostin.calc")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.andrey.kostin.calc")));
            }*/

/*массив кнопок
        Button[] buttonArray = {
                (Button) findViewById(R.id.button1),
                (Button) findViewById(R.id.button2),
                (Button) findViewById(R.id.button3),
                (Button) findViewById(R.id.button4),
                (Button) findViewById(R.id.button5),
                (Button) findViewById(R.id.button6),
                (Button) findViewById(R.id.button7) };
        for (Button button : buttonArray) {
            button.setText("text");
        }
*/

        //привязываем переменные к элементам вью
        b0=(Button)findViewById(R.id.b0);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        b4=(Button)findViewById(R.id.b4);
        b5=(Button)findViewById(R.id.b5);
        b6=(Button)findViewById(R.id.b6);
        b7=(Button)findViewById(R.id.b7);
        b8=(Button)findViewById(R.id.b8);
        b9=(Button)findViewById(R.id.b9);
        bc=(Button)findViewById(R.id.bc);
        bce=(Button)findViewById(R.id.bce);
        bstepen=(Button)findViewById(R.id.bstepen);
        bdrob=(Button)findViewById(R.id.bdrob);
        bmult=(Button)findViewById(R.id.bmult);
        bpi =(Button)findViewById(R.id.bpi);
        broot=(Button)findViewById(R.id.broot);
        bplus=(Button)findViewById(R.id.bplus);
        bminus=(Button)findViewById(R.id.bminus);
        bdiv=(Button)findViewById(R.id.bdiv);
        bsign=(Button)findViewById(R.id.bsign);
        bdot=(Button)findViewById(R.id.bdot);
        bresult=(Button)findViewById(R.id.bresult);
        bdel=(Button)findViewById(R.id.bdel);
        bMplus=(Button)findViewById(R.id.bMplus);
        bMminus=(Button)findViewById(R.id.bMminus);
        bMR=(Button)findViewById(R.id.bMR);
        bMC=(Button)findViewById(R.id.bMC);

        okno=(TextView)findViewById(R.id.okno);

        //назначаем онкликлиснер кнопкам
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bc.setOnClickListener(this);
        bce.setOnClickListener(this);
        bstepen.setOnClickListener(this);
        bdrob.setOnClickListener(this);
        bmult.setOnClickListener(this);
        bpi.setOnClickListener(this);
        broot.setOnClickListener(this);
        bplus.setOnClickListener(this);
        bminus.setOnClickListener(this);
        bdiv.setOnClickListener(this);
        bsign.setOnClickListener(this);
        bdot.setOnClickListener(this);
        bresult.setOnClickListener(this);
        bdel.setOnClickListener(this);
        bMplus.setOnClickListener(this);
        bMminus.setOnClickListener(this);
        bMR.setOnClickListener(this);
        bMC.setOnClickListener(this);



//bresult.setBackgroundResource(R.drawable.buttonstyle); //установка бекграунда кнопки

// Узнаем размеры экрана из ресурсов
        myDisplay = getResources().getDisplayMetrics();
        sizeY=myDisplay.heightPixels/9;    //Высчитываем размер 1/9 высоты экрана для размера кнопок
        textheight=myDisplay.heightPixels/20; //Высчитываем размер 1/20 высоты экрана для размера текста

//программно задаем размер кнопок
        b0.setHeight(sizeY);
        b1.setHeight(sizeY);
        b2.setHeight(sizeY);
        b3.setHeight(sizeY);
        b4.setHeight(sizeY);
        b5.setHeight(sizeY);
        b6.setHeight(sizeY);
        b7.setHeight(sizeY);
        b8.setHeight(sizeY);
        b9.setHeight(sizeY);
        b0.setHeight(sizeY);
        bsign.setHeight(sizeY);
        bdot.setHeight(sizeY);
        bresult.setHeight(sizeY);
        bplus.setHeight(sizeY);
        bminus.setHeight(sizeY);
        bdiv.setHeight(sizeY);
        bmult.setHeight(sizeY);
        bpi.setHeight(sizeY);
        broot.setHeight(sizeY);
        bstepen.setHeight(sizeY);
        bdrob.setHeight(sizeY);
        bc.setHeight(sizeY);
        bce.setHeight(sizeY);
        bdel.setHeight(sizeY);
        bMplus.setHeight(sizeY);
        bMminus.setHeight(sizeY);
        bMC.setHeight(sizeY);
        bMR.setHeight(sizeY);

        okno.setHeight(sizeY + sizeY / 2);//говнокод для задания высоты окна результата 1.5 размера кнопки
//восстанавливаем результат предыдущего вычисления в окна вывода из настроек
            numclick=false;
            stroka=sp.getString("memstroka", "0"); //считываем содержимое предыдущего результата из настроек
            if(!stroka.equals("")) num1=Double.parseDouble(stroka); //если строка не пуста занести содержимое строки в переменную

//назначаем размер шрифта пропорционально размера экрана
        b0.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b5.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b6.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b7.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b8.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        b9.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bc.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bce.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bstepen.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bdrob.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bmult.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bpi.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        broot.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bplus.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bminus.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bdiv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bsign.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bdot.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bresult.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bdel.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bMplus.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bMminus.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bMC.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);
        bMR.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight);

//        okno.setTextSize(TypedValue.COMPLEX_UNIT_PX, textheight); задаем высоту текста окна как на кнопках - сейчас не использую, задаю из dimens для разных размеров экрана
// okno.setBackground(this.getResources().getDrawable(R.drawable.round)); задаем окну фон из drawable работает только начиная с  API 16

//узнаем размеры экрана и плотность пикселей
        Log.d(TAG, "Ширина: " + myDisplay.widthPixels + "\n" +
                "Высота: " + myDisplay.heightPixels + "\n"+
                "Плотность: " + myDisplay.density+"\n"+
                "Размер текста: " +textheight+"\n");



  /*      Resources res = getResources(); //черновик обращения к фону окна
        Drawable drawOkno = res.getDrawable(R.drawable.round);
*/


    }catch(Exception e) { Log.d(TAG, "\n"+"Перехвачено исключение в методе onСreate"); }//отлавливаем исключение в методе onCreate
    }

@Override
    protected void onResume(){
    try{
        floatnumber=Integer.valueOf(sp.getString("floatnumber", "6"));         //считываем содержимое строки параметра флоатнамбер из файла настроек и преобразуем в интеджер( по умолчанию 6)

        fontstyle=sp.getString("fontstyle", "zekton.ttf");         //считываем содержимое строки параметра фонтстиль из файла настроек (по умолчанию если настройка пуста назначаем шрифт зектон)
        crystalfont = Typeface.createFromAsset(getAssets(), fontstyle); //Назначаем переменной стиль шрифта crystal
        okno.setTypeface(crystalfont);             //задаем окну шрифт из ассетов через переменную кристалфонт

        memstroka=sp.getString("memstroka", "0"); //считываем содержимое предыдущего результата вычислений из настроек
        okno.setText(memstroka);                                                       //выводим строку в окно вью

        Log.d(TAG,"Содержимое файла настроек: floatnumber "+floatnumber+"\n"+
                "fontstyle "+fontstyle+"\n"+
                "memstroka "+memstroka+"\n");

    }catch(Exception e) { Log.d(TAG, "\n"+"Перехвачено исключение в методе onResume"); }//отлавливаем исключение в методе onResume
    super.onResume();
}


//мой обычный обработчик меню
@Override
    public boolean onCreateOptionsMenu(Menu menu) {         //Необходимая функция Создает меню из файла мейнменю.хмл
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //Необходимая функция Обрабатываем нажатия на пункты меню
        try{
        int id = item.getItemId();

switch (id){

case R.id.preferences:
        intent = new Intent(this,PrefActivity.class);   //даем на вход интента текущий контекст и класс активити Преференсис
        startActivity(intent);                      //запускаем вторую активити через интент
        return true;

    case R.id.close_app:    //обрабатываем пункт меню "Закрыть"
        finish();           //команда закрытия приложения
        break;

 /*   Этот пункт меню Оценить не использую, перенес его в преференсес, а здесь оставил запись на всякий случай
    case R.id.rate:         //обрабатываем пункт меню "Оценить приложение"

        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.andrey.kostin.calc"));
        startActivity(intent);
        break;
*/
    default:break;
        }

        }catch(Exception e) { Log.d(TAG, "\n"+"Перехвачено исключение в меню"); }//отлавливаем исключение при нажатии пункта меню
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View v) {
        int id;        //переменная для определения ид нажатой кнопки
        String symbol; //переменная в которой будем хранить символ с кнопки

        try{



        id=v.getId();  //присваиваем переменной ид нажатой кнопки

        symbol=((Button)v).getText().toString();//берем текст с кнопки в переменную символ

        switch(id){     //определяем какая кнопка была нажата и выполняем соответствующие действия
            case R.id.b0:
            case R.id.b1:
            case R.id.b2:
            case R.id.b3:
            case R.id.b4:
            case R.id.b5:
            case R.id.b6:
            case R.id.b7:
            case R.id.b8:
            case R.id.b9:               //если нажата цифра то добавляем ее в текствью
                if ((stroka.startsWith("0")&&!stroka.contains("."))||!numclick) stroka="0";//если строка начинается с 0 без . или нажата кнопка мат операций строка=0
                if(stroka.equals("0"))  stroka = symbol;
                                        else {if(stroka.length()<dlinaChisla)stroka = stroka + symbol;}//если в строке менее dlinaChisla символов добавляем к текущей строке символ взятый с нажатой кнопки
                okno.setText(stroka); //выводим строку в окно вью
                numclick=true;
                break;

            case R.id.bMC:           //если нажата кнопка "MC" чистим ячейку памяти mem
                mem=0;
                break;

            case R.id.bMplus:           //если нажата кнопка "M+" добавляем текущее значение в mem
                numclick=false;
                stroka=okno.getText().toString();           //берем текст напрямую из текствью
                if(!stroka.equals("")) {
                    numtemp = Double.parseDouble(stroka); //преобразование типов из стринг в дабл
                    mem=mem+numtemp;                        //добавляем значение к ячейке памяти
                }
                break;

            case R.id.bMminus:           //если нажата кнопка "M-" вычитаем текущее значение из mem
                numclick=false;
                stroka=okno.getText().toString();           //берем текст напрямую из текствью
                if(!stroka.equals("")) {
                    numtemp = Double.parseDouble(stroka); //преобразование типов из стринг в дабл
                    mem=mem-numtemp;                        //вычитаем значение из ячейки памяти
                }
                break;

            case R.id.bMR:           //если нажата кнопка "MR" выводим значение из ячейки памяти в окно
                numclick=false;
                vivod(mem);                         //выводим число в окно вью
                break;

            case R.id.bce:           //если нажата кнопка "СE" сброс, то заносим в строку 0
                numclick=false;
                stroka="0";
                okno.setText(stroka); //выводим строку в окно вью
                break;

            case R.id.bc:           //если нажата кнопка "С" сброс, то заносим в строку 0, и обнуляем все переменные
                numclick=false;
                stroka="0";
                num1=num2=numtemp=0.0;
                okno.setText(stroka); //выводим строку в окно вью
                break;

            case R.id.bsign:        //если нажата кнопка "+-" то берем текст из текствью и меняем ему знак
                numclick=false;
                stroka=okno.getText().toString();           //берем текст напрямую из текствью
                if(!stroka.equals("")) {
                    numtemp = Double.parseDouble(stroka); //преобразование типов из стринг в дабл
                    if (numtemp != 0)
                        numtemp = -numtemp;                   // если не ноль то инвертируем знак числа
                    vivod(numtemp);                         //выводим число в окно вью
                }
                    break;

            case R.id.bdel:                          //если нажата кнопка удаления символа, удалить последний символ
                numclick=true;
                stroka=okno.getText().toString();       //берем текст напрямую из текствью
                    if (!stroka.equals("")&& stroka.length() > 0) {                  //если строка не пустая и длиной больше 0 то выполнить удаление последнего символа
                        stroka = stroka.substring(0, stroka.length() - 1);//удаляем последний символ из строки
                        if (stroka.length() == 0) {
                            stroka = "0";                }         //если длина строки равна нулю вывести в строку ноль
                        okno.setText(stroka);                                   //выводим строку в окно вью
                    }
                break;

            case R.id.bdot:                     //добавляем к числу дробную часть
                numclick=true;
                stroka=okno.getText().toString(); //берем текст напрямую из текствью
                if(!stroka.contains(".")) stroka = stroka + ".";//если в строке нет точки то по нажатию клавиши "." добавляем точку
                okno.setText(stroka);             //выводим строку в окно вью
                break;

            case R.id.broot:                    //вычисление квадратного корня
                numclick=false;
                if(!stroka.equals("")) num1=Double.parseDouble(stroka); //если строка не пуста выполнить
                if(num1>=0) num1=Math.sqrt(num1); else mytoast(getResources().getString(R.string.root));//если число больше нуля вычислить корень, иначе вывести тост с ошибкой
                vivod(num1);          //выводим число в окно вью
                break;

            case R.id.bpi:
                numclick=false;
                numtemp=Math.PI;
                vivod(numtemp);          //выводим число в окно вью
                break;

            case R.id.bstepen:
                numclick=false;
                if(!stroka.equals("")) {
                    numtemp = Double.parseDouble(stroka); //если строка не пуста выполнить
                    numtemp = numtemp * numtemp;
                    vivod(numtemp);          //выводим число в окно вью
                }
                break;

            case R.id.bdrob:
                numclick=false;
                if(!stroka.equals("")) {
                    numtemp = Double.parseDouble(stroka); //если строка не пуста выполнить
                    numtemp = 1 / numtemp;
                    vivod(numtemp);          //выводим число в окно вью
                }
                    break;

            case R.id.bminus:
                numclick=false;
                btouch="bminus";
                if(!stroka.equals("")) num1=Double.parseDouble(stroka); //если строка не пуста выполнить
                break;

            case R.id.bplus:
                numclick=false;
                btouch="bplus";
                if(!stroka.equals("")) num1=Double.parseDouble(stroka); //если строка не пуста выполнить
                break;

            case R.id.bmult:
                numclick=false;
                btouch="bmult";
                if(!stroka.equals("")) num1=Double.parseDouble(stroka); //если строка не пуста выполнить
                break;

            case R.id.bdiv:
                numclick=false;
                btouch="bdiv";
                if(!stroka.equals("")) num1=Double.parseDouble(stroka); //если строка не пуста выполнить
                break;

            case R.id.bresult:
                numclick=false;
                result();     //вызываем функцию обработки кнопки равно
                break;

            default:break;
                    }
        }catch(Exception e) { Log.d(TAG, "\n"+"Перехвачено исключение при нажатии кнопки"); }//отлавливаем исключения при нажатии кнопки

    }

    private void result() { //функция обработки нажатия кнопки равно
        try{
        switch (btouch){
            case "bplus":
                if(!stroka.equals("")){                  //если строка не пуста выполнить суммирование
                num2=Double.parseDouble(stroka); //преобразование типов из стринг в дабл
                num1=num1+num2;
                    vivod(num1);          //выводим число в окно вью
                }
                break;

            case "bminus":
                if(!stroka.equals("")){
                    num2=Double.parseDouble(stroka); //преобразование типов из стринг в дабл
                    num1=num1-num2;                 //из числа занесенного в нум1 при предыдущем нажатии кнопки минус отнять только что полученное в нум2 число
                    vivod(num1);          //выводим число в окно вью
                }
                break;

            case "bmult":
                if(!stroka.equals("")){
                    num2=Double.parseDouble(stroka); //преобразование типов из стринг в дабл
                    num1=num1*num2;                 //умножение чисел нум1 введенных при нажатии кнопки умножить на число введенное до нажатия кнопки равно
                    vivod(num1);          //выводим число в окно вью
                }
                break;

            case "bdiv":
                if(!stroka.equals("")){
                    num2=Double.parseDouble(stroka); //преобразование типов из стринг в дабл
                    if(num2!=0){ num1=num1/num2; vivod(num1);} //деление числа  нум1 введенного при нажатии кнопки делить на число введенное до нажатия кнопки равно. затем вывод
                      else
                    mytoast(getResources().getString(R.string.zero));//сообщение о делении на ноль
                }
                break;

            default:break;
        }
    }catch(Exception e) { Log.d(TAG, "\n"+"Перехвачено исключение в функции результат"); }//отлавливаем исключения в функции результат
    }

    private void mytoast(String alert){             //функция выводящая тост
    try{
        mytoast = Toast.makeText(this, alert, Toast.LENGTH_SHORT);
        mytoast.setGravity(Gravity.CENTER, 0, 0);
        mytoast.show();
    }catch(Exception e) { Log.d(TAG, "\n"+"Перехвачено исключение в функции Тост"); }//отлавливаем исключения в функции Тост
    }

    private void vivod(double n){               //функция выводящая в окно результат
    try{
        stroka=(Double.toString(n));// переводим дабл в стринг, для последующего использования в округлении
        okrugl= new BigDecimal(stroka).setScale(floatnumber,BigDecimal.ROUND_HALF_UP); //округление числа до заданного в настройка количества знаков после запятой
        stroka=(okrugl.toString());                                                  //преобразование типов из бигдецимал в стринг
        //цикл для высчитывания количества нулей после запятой для откидывания пустых хвостов
        for (i = 1; i <= floatnumber+1; i++) { if((stroka.endsWith("0")&&(stroka.contains(".")))|(stroka.endsWith("."))) stroka=stroka.substring(0, stroka.length()-1);}//если в (конце строки "0"и есть в строке ".") или (строка заканчивается на ".")откинуть один символ

        if(stroka.length()>dlinaChisla*2){ stroka="0";mytoast(getResources().getString(R.string.big));}//если получили число длиной вдвое больше dlinaChisla символов - обнуляем результат и показываем сообщение
        okno.setText(stroka);                                                       //выводим строку в окно вью



    }catch(Exception e) { Log.d(TAG, "\n"+"Перехвачено исключение в функции вывода"); }//отлавливаем исключения в функции вывода
    }


    @Override
    protected void onPause() {         //запись результата в файл настроек
        super.onPause();

        SharedPreferences.Editor ed = sp.edit();//чтобы редактировать данные, необходим объект Editor – получаем его из sp
        ed.putString("memstroka", okno.getText().toString());      //В метод putString указываем наименование переменной  и значение взятое из окна тектсвью
        ed.apply();                             //Чтобы данные сохранились, необходимо выполнить apply.
    }

}
