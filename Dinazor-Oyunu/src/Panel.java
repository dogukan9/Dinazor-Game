import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
class Ates{
    private int x;
    private int y;

    public Ates(int x,int y) {
        this.x = x;
         this.y = y;
    }

    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
   
    
}


public class Panel extends JPanel implements KeyListener,ActionListener{
    Random random=new Random();
Timer timer=new Timer(5, this);
 private int gecen_sure=0;
private int dinazorX=0;
private int dirdinazorX=20;
private int atesdirY=1;
private int k=0;
private int mermidir=1;
  
private ArrayList<Ates> atesler=new ArrayList<Ates>();
private ArrayList<Ates> mermi=new ArrayList<Ates>();
private  BufferedImage image;

   public boolean kontrol(){
    for(Ates ates :atesler){
        if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(dinazorX,600,30,20))){
            return true;
        }
    }
    return false;
    
     
   }
   public boolean check(){
    for(Ates ates : atesler){
         for(Ates boncuk :mermi){
        if(new Rectangle(boncuk.getX(),boncuk.getY(),30,20).intersects(new Rectangle(ates.getX(),ates.getY(),10,20))){
            k+=10 ;
     return true;
        }
    }
    }
    return false;
    
     
   }
   public void Ses(){
       
        try {
            AudioInputStream stream=AudioSystem.getAudioInputStream(new File("yumruk.wav"));
            Clip clip=AudioSystem.getClip();
            clip.open(stream);
             clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public Panel() {
       
  try {
        image=ImageIO.read(new FileInputStream(new File("dinazor.jpg")));
       
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
    }
        setBackground(Color.GRAY);
        timer.start();
    }

          
  

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
  gecen_sure+=5;
     g.drawImage(image,dinazorX,600,image.getWidth()/10,image.getHeight()/10,this);
     for(Ates ates:atesler){
        if(ates.getY()<=0){
            atesler.remove(ates);
        }
    }
    g.setColor(Color.BLUE);
 
        for(Ates boncuk:mermi){
        if(boncuk.getY()<=0){
            mermi.remove(boncuk);
        
    }
        }
         for(Ates boncuk:mermi){
            g.setColor(Color.red);
        g.fillOval(boncuk.getX(),boncuk.getY(),20,20);
    }
         g.setColor(Color.BLUE);
       for(Ates ates:atesler){
        g.fillRect(ates.getX(),ates.getY(),10,20);
    }
        if(kontrol()){
        timer.stop();
        String message="Kaybettiniz!!\n"+"Geçen süre="+gecen_sure/1000+" saniye\n"+"Score="+k;
              
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }
 if(check()){
       
         for(Ates ates :atesler){
             atesler.remove(ates);
             break;
         }
    
        }
        
    }
    
   
   
     @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c=e.getKeyCode();
        if(c==KeyEvent.VK_LEFT){
       if(dinazorX<=0){
              dinazorX=0;
          }
       
          else{
             dinazorX-=dirdinazorX;
           
      atesler.add(new Ates(random.nextInt(750),0));
        
          }
        }
    else  if(c==KeyEvent.VK_RIGHT){
          if(dinazorX>=750){
              dinazorX=750;
          }
          else{
             dinazorX+=dirdinazorX;
                 atesler.add(new Ates(random.nextInt(750),0));
                
          }
      }
         else   if(c==KeyEvent.VK_SPACE){
           mermi.add(new Ates(dinazorX+15,570));
                   Ses();
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   
              
                  
      
       
            for(Ates bates: mermi){
        bates.setY(bates.getY()-mermidir);

    }
            
      
            
          
    for(Ates ates: atesler){
       
        ates.setY(ates.getY()+atesdirY);

    }
      
        
        repaint();
    }

   
    
}
