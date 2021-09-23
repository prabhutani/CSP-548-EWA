
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WearableList")

public class WearableList extends HttpServlet {

	/* Trending Page Displays all the Wearables and their Information in Laptop Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the Wearables type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("catid");
        // String CategoryId = request.getParameter("catid");
		HashMap<String, Wearable> hm = new HashMap<String, Wearable>();

		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.wearables);
			name = "";
		} 
		else 
		{
			if(CategoryName.equals("fitwatch")) 
			{	
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("fitwatch"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="fitwatch";
			} 
			else if (CategoryName.equals("smartwatch"))
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("smartwatch"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "smartwatch";
			} 
			else if (CategoryName.equals("headphone")) 
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("headphone"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "headphone";
			}
            else if (CategoryName.equals("vr")) 
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("vr"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "vr";
			}
            else if (CategoryName.equals("pettracker")) 
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("pettracker"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "pettracker";
			}
	    }

		/* Header, Left Navigation Bar are Printed.

		All the wearables and wearable information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " Wearables</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Wearable> entry : hm.entrySet()) {
			Wearable Wearable = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + Wearable.getName() + "</h3>");
			pw.print("<strong>" + Wearable.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/wearables/"
					+ Wearable.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
