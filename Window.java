package jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    // attributs
    private int width, height;
    private String title;
    private long glfwWindow;


    private static Window window = null;



    // constructor
    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Mario";
    }

    public static Window get() {

        if(Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    /*public static void setWindow(Window window) {
        Window.window = window;
    }
    *
     */

    public void run(){
        System.out.println("hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        //  Terminate GLFW and the free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


    public void init(){

        // set up the error call
        GLFWErrorCallback.createPrint(System.err).set();

        //initialize GLFW
        if(!glfwInit()){
            throw new IllegalStateException("unable to initialize GLFW");

        }

        // configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // create the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);


        // make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);

        // enable v-sync
        glfwSwapInterval(1);

        // make the window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();

    }

    public void loop(){
        while (!glfwWindowShouldClose(glfwWindow)){

            // pull event
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }
}
