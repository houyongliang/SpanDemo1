package com.hyl.mis.spandemo1;


import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Rasterizer;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RasterizerSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private SpannableStringBuilder mWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        setTv1();
        setTv2();
        setTv3();
        setTv4();
        setTv5();
    }
    // //SpannableString与SpannableStringBuilder区别
    //使用SpannableString，必须一次传入，构造完成

    private void setTv5() {
        //让URLSpan可以点击
        SpannableString spanText = new SpannableString("设置网络");

        spanText = new SpannableString("URLSpan -- 设置超链接");
        spanText.setSpan(new URLSpan("http://www.baidu.com"), 10, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv5.append("\n");
        tv5.append(spanText);

        tv5.setMovementMethod(new LinkMovementMethod());
    //ClickableSpan 点击
        spanText.setSpan(new ClickableSpan() {

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置指定区域的文字变蓝色
                ds.setColor(Color.BLUE);
                //设置指定区域的文字有下划线
                ds.setUnderlineText(true);
            }

            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this,"吐一下",Toast.LENGTH_SHORT).show();


            }
        }, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //加上这句话，才有点击效果
        tv5.setMovementMethod(LinkMovementMethod.getInstance());
        //把spannableString设置到TextView上
        tv5.setText(spanText);

    }

    private void setTv4() {
        //RelativeSizeSpan 相对大小（文本字体）
        SpannableString spanText = new SpannableString("RelativeSizeSpan");
//参数proportion:比例大小
        spanText.setSpan(new RelativeSizeSpan(2.5f), 3, 4,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv4.append("\n");
        tv4.append(spanText);
        //ScaleXSpan 基于x轴缩放
       spanText = new SpannableString("ScaleXSpan");
        spanText.setSpan(new ScaleXSpan(20),0,6,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv4.append("\n");
        tv4.append(spanText);
        //SubscriptSpan 下标（数学公式会用到）

        spanText = new SpannableString("SubscriptSpan -- BOKEWANG下标");
        spanText.setSpan(new SubscriptSpan(), 6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv4.append("\n");
        tv4.append(spanText);
        //SuperscriptSpan 上标（数学公式会用到）
        spanText = new SpannableString("SuperscriptSpan -- 上标");
        spanText.setSpan(new SuperscriptSpan(), 6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv4.append("\n");
        tv4.append(spanText);
        //TextAppearanceSpan 文本外貌（包括字体、大小、样式和颜色）
        spanText = new SpannableString("TextAppearanceSpan --系统样式上进行修改 ");
//若需自定义TextAppearance，可以在系统样式上进行修改
        spanText.setSpan(new TextAppearanceSpan(this, android.R.style.TextAppearance_Medium),
                6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv4.append("\n");
        tv4.append(spanText);
        //TypefaceSpan 文本字体
        spanText = new SpannableString("TypefaceSpan -- ");
//若需使用自定义字体，可能要重写类TypefaceSpan
        spanText.setSpan(new TypefaceSpan("monospace"), 3, 10,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv4.append("\n");
        tv4.append(spanText);

    }

    private void setTv3() {
        //AbsoluteSizeSpan 绝对大小（文本字体）
        SpannableString spanText = new SpannableString("AbsoluteSizeSpan");
        spanText.setSpan(new AbsoluteSizeSpan(50, true), 0, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv3.append("\n");
        tv3.append(spanText);
        //DynamicDrawableSpan 设置图片，基于文本基线或底部对齐。
        DynamicDrawableSpan drawableSpan =
                new DynamicDrawableSpan(DynamicDrawableSpan.ALIGN_BASELINE) {
                    @Override
                    public Drawable getDrawable() {
                        Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
                        d.setBounds(0, 0, 50, 50);
                        return d;
                    }
                };
        DynamicDrawableSpan drawableSpan2 = new DynamicDrawableSpan(
                DynamicDrawableSpan.ALIGN_BOTTOM) {
            @Override
            public Drawable getDrawable() {
                Drawable d = getResources().getDrawable(R.mipmap.guide3);
                d.setBounds(0, 0, 50, 100);
                return d;
            }
        };
        spanText.setSpan(drawableSpan, 3, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText.setSpan(drawableSpan2, 7, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv3.append("\n");
        tv3.append(spanText);
        //ImageSpan 图片
        spanText = new SpannableString("ImageSpan");
        Drawable mDrawable=getResources().getDrawable(R.mipmap.ic_launcher);
        mDrawable.setBounds(0, 0, 50, 50);
        spanText.setSpan(new ImageSpan(mDrawable), 3, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv3.append("\n");
        tv3.append(spanText);
    }

    private void setTv2() {
        //MaskFilterSpan 修饰效果，
        SpannableString spanText = new SpannableString("MaskFilterSpan -- http://orgcent.com");
        int length = spanText.length();
//模糊(BlurMaskFilter) 模糊  Blur模糊，不清楚
        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(new BlurMaskFilter(3, BlurMaskFilter.Blur.OUTER));
        spanText.setSpan(maskFilterSpan, 0, length - 10, Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);
        tv2.append("\n");
        tv2.append(spanText);
//浮雕(EmbossMaskFilter)浮雕
        maskFilterSpan = new MaskFilterSpan(new EmbossMaskFilter(new float[]{1,1,3}, 1.5f, 8, 3));
        spanText.setSpan(maskFilterSpan, length - 10, length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv2.append("\n");
        tv2.append(spanText);
        //RasterizerSpan 光栅效果
        spanText = new SpannableString("RasterizerSpan");
        spanText.setSpan(new RasterizerSpan(new Rasterizer()), 0, 7, Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);
        tv2.append("\n");
        tv2.append(spanText);
        //StrikethroughSpan 删除线（中划线）
        spanText = new SpannableString("StrikethroughSpan");
        spanText.setSpan(new StrikethroughSpan(), 0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv2.append("\n");
        tv2.append(spanText);
        //UnderlineSpan 下划线
        spanText = new SpannableString("UnderlineSpan");
        spanText.setSpan(new UnderlineSpan(), 0, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv2.append("\n");
        tv2.append(spanText);
        //
    }

    private void setTv1() {// 1.BackgroundColorSpan 背景色
        mWord = new SpannableStringBuilder("别人是最好的老师。。");
        mWord.setSpan(new BackgroundColorSpan(Color.RED),0,
                mWord.length(),Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//设置背景
        tv1.append("\n");
        tv1.append(mWord);

        //ClickableSpan  文本可点击，有点击事件
        //ForegroundColorSpan 文本颜色（前景色）
        mWord=new SpannableStringBuilder("每天进步一点点- http://note.youdao.com/share/?id=a18a41aff5961dffe975fd30705a5e6f&type=note#/");
        mWord.setSpan(new ForegroundColorSpan(Color.GREEN),0,mWord.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.append("\n");
        tv1.append(mWord);



        //使用SpannableStringBuilder,可以使用append()再添加 别的用法和SpannableString一致
         mWord = new SpannableStringBuilder();
        mWord.append("欢迎光临"+"\n");
        mWord.append("度假小村"+"\n");
        mWord.append("旅游"+"\n");
    //StyleSpan 字体样式：粗体、斜体等
        StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
        mWord.setSpan(span, 0, mWord.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv2.setText(mWord);

    }
}
