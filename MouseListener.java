package jade;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {

    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPas, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    // constructor


    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPas = 0.0;
        this.yPos = 0.0;
        this.lastY = 0.0;
        this.lastX = 0.0;

    }

    // get method
    public static MouseListener get(){
        if(MouseListener.instance == null){
            MouseListener.instance = new MouseListener();

        }
        return instance;
    }


    public static void mousePosCallback(long window, double xpos, double ypos){
        get().lastX = get().xPas;
        get().lastY = get().yPos;

        get().xPas = xpos;
        get().yPos = ypos;

    }


    public static void mouseButtonCallback(long window, int button, int action, int mods){
        if(action == GLFW_PRESS){
            get().mouseButtonPressed[button] = true;

        }else if (action == GLFW_RELEASE){
            get().mouseButtonPressed[button] = false;
            get().isDragging = false;
        }
    }
}
