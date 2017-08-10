package com.example.a8_7opengltest;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wuqi on 2017/8/7.
 */

public class MyRenderer implements GLSurfaceView.Renderer {


    //投影变换,矩阵变换
    private static final String VERTEX_SHADER =
            "attribute vec4 vPosition;\n"
                    + "void main() {\n"
                    + "  gl_Position = vPosition;\n"
                    + "}";
    private static final String FRAGMENT_SHADER =
            "precision mediump float;\n"
                    + "void main() {\n"
                    + "  gl_FragColor = vec4(0.5, 0, 0, 1);\n"
                    + "}";

    //坐标原点在屏幕中心，z轴
    private static final float[] VERTEX = {
            0, 1, 0,  // top
            -0.5f, -1, 0,  // bottom left
            0, 0, 0,  // bottom right
    };

    private final FloatBuffer mVertexBuffer;

    private int mProgram;
    private int mPositionHandle;

    MyRenderer() {
        mVertexBuffer = ByteBuffer.allocateDirect(VERTEX.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(VERTEX);
        mVertexBuffer.position(0);
    }

    /**
     * 加载type shaderCode
     * @param type GLE20类型
     * @param shaderCode 渲染的矩阵
     * @return
     */
    static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    /**
     * 在 surface 创建时被回调，通常用于进行初始化工作，只会被回调一次；
     * @param unused
     * @param config
     */
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        //和普通的 view 利用 canvas 来绘制不一样，OpenGL 需要加载 GLSL 程序，让 GPU 进行绘制

        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        //创建 GLSL 程序
        mProgram = GLES20.glCreateProgram();
        //加载 shader 代码
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER);
        //attatch shader 代码
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        //链接 GLSL 程序
        GLES20.glLinkProgram(mProgram);

        //使用 GLSL 程序
        GLES20.glUseProgram(mProgram);
        //获取 shader 代码中的变量索引
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用 vertex
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //绑定 vertex 坐标值
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false,
                12, mVertexBuffer);
    }

    /**
     * 在每次 surface 尺寸变化时被回调，注意，第一次得知 surface 的尺寸时也会回调
     * @param unused
     * @param width
     * @param height
     */
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        //设置 Screen space 的大小
        GLES20.glViewport(0, 0, width, height);
    }

    /**
     * 则在绘制每一帧的时候回调
     * @param unused
     */
    @Override
    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        //开始绘制
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }
}
