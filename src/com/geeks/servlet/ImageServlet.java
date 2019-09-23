package com.geeks.servlet;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String imagePath;

    public void init() throws ServletException {

        // Define base path somehow. You can define it as init-param of the servlet.
        this.imagePath = "D:\\apache-tomcat-8.5.33\\webapps\\ROOT\\images";

   }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		   String requestedImage = request.getPathInfo();
		   	if (requestedImage == null) {
	            // Do your thing if the image is not supplied to the request URI.
	            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
	            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            return;
	        }

	        // Decode the file name (might contain spaces and on) and prepare file object.
	        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));
	        
	        // Check if file actually exists in filesystem.
	        if (!image.exists()) {
	            // Do your thing if the file appears to be non-existing.
	            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
	            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            return;
	        }

	        // Get content type by filename.
	        String contentType = getServletContext().getMimeType(image.getName());

	        // Check if file is actually an image (avoid download of other files by hackers!).
	        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
	        if (contentType == null || !contentType.startsWith("image")) {
	            // Do your thing if the file appears not being a real image.
	            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
	            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            return;
	        }

	        // Init servlet response.
	        response.reset();
	        response.setContentType(contentType);
	        response.setHeader("Content-Length", String.valueOf(image.length()));

	        System.out.println(image.toPath());
	        // Write image content to response.
	        Files.copy(image.toPath(), response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
