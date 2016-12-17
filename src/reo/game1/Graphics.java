package reo.game1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class Graphics extends SurfaceView{
	public SurfaceHolder holder;
	public static Canvas canvas;
	public static Paint paint;
	public TextView text;
	public int linecolor;
	
// コンストラクタ
	public Graphics(SurfaceHolder holder, Context context){
		super(context);
		this.holder = holder;
		canvas = new Canvas();
		paint = new Paint();
		text = new TextView(context);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);
		paint.setTextSize(18.0f);
	}
	
// グラフィクス
		public void setGraphics(SurfaceHolder holder){
			paint.setAntiAlias(true);
			paint.setTextSize(18.0f);
		}
		
//ロック
	    public void lock() {
	        canvas=holder.lockCanvas();
	    }
	    
//アンロック
	    public void unlock() {
	        holder.unlockCanvasAndPost(canvas);
	    }
	    
//色の指定
	    public void settingColor(int color) {
	        paint.setColor(color);
	    }

//矩形の描画
	    public void drawRect(int left, int top, int right, int bottom) {
	        paint.setStyle(Paint.Style.STROKE);
	        canvas.drawRect(left, top, right, bottom ,paint);
	    }
	    
//矩形の塗りつぶし描画
	    public void drawRectfill(int left, int top, int right, int bottom){
			settingColor(Color.BLACK);
			paint.setStyle(Paint.Style.FILL);
			canvas.drawRect(left, top, right, bottom, paint);
	    }
	    
//ビットマップの描画
	    public void drawBitmap(Bitmap bitmap,int left,int top, int right, int bottom) {
	        int w=bitmap.getWidth();
	        int h=bitmap.getHeight();
	        Rect src=new Rect(0,0,w,h);
	        Rect dst=new Rect(left, top, right, bottom);
	        canvas.drawBitmap(bitmap,src,dst,null);
	    }

//文字列の描画
	    public static void drawingText(String string,int x,int y) {
	    	onDraw(canvas ,string, x, y);
	    }

//文字列の描画（赤）
    public static void drawingText1(String string,int x,int y) {
    	redLine();
    	onDraw(canvas ,string, x, y);
    }
    
//文字列の描画（黄色）
    public static void drawingText2(String string,int x,int y) {
    	yellowLine();
    	onDraw(canvas ,string, x, y);
    }
    
//文字列の描画（緑）
    public static void drawingText3(String string,int x,int y) {
    	greenLine();
    	onDraw(canvas ,string, x, y);
    }
    
//文字列の描画（青）
    public static void drawingText4(String string,int x,int y) {
    	blueLine();
    	onDraw(canvas ,string, x, y);
    }
    
//onDraw関数(オーバーライドすると呼び出されない！！！)
	    protected static void onDraw(Canvas canvas ,String string, int x, int y){
	    	paint.setTextSize(18.0f);
	      	canvas.drawText(string, x, y, paint);
	    }
	    
// 赤い文字列を書く関数
    public static void redLine(){
    	paint.setColor(Color.RED);
    }
// 黄色い文字列を書く関数
    public static void yellowLine(){
    	paint.setColor(Color.YELLOW);
    }
// 緑色の文字列を書く関数
    public static void greenLine(){
    	paint.setColor(Color.GREEN);
    }
// 青い文字列を書く関数
    public static void blueLine(){
    	paint.setColor(Color.BLUE);
    }
// 白い文字列を書く関数
    public static void whiteLine(){
    	paint.setColor(Color.WHITE);
    }
}