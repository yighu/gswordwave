import org.grails.plugins.wave.*
import com.google.wave.api.*
/**
 * {@link MyRobotService}
 *
 * Robot Template Implementation
 */
class MyRobotService implements GrailsWaveRobot {

    boolean transactional = true

	/**
	 * Set these properties for your custom robot. 
	 * keep in mind to remove capabilities from the 
	 * list below which your robot has not implemented.
	 */
    static robotName = "GSword"
	static robotVersion = "1"
	static imageUrl = "http://a1.twimg.com/profile_images/406257440/twitterProfilePhoto.jpg"
	static profileUrl = "http://gswordwave.googlewave.com"
	static robotCapabilities = [EventType.WAVELET_BLIP_CREATED,
								EventType.WAVELET_BLIP_REMOVED,
								EventType.WAVELET_PARTICIPANTS_CHANGED,
								EventType.WAVELET_SELF_ADDED,
								EventType.WAVELET_SELF_REMOVED,
								EventType.WAVELET_TIMESTAMP_CHANGED,
								EventType.WAVELET_TITLE_CHANGED,
								EventType.WAVELET_VERSION_CHANGED,
								EventType.BLIP_CONTRIBUTORS_CHANGED,
								EventType.BLIP_DELETED,
								EventType.BLIP_SUBMITTED,
								EventType.BLIP_TIMESTAMP_CHANGED,
								EventType.BLIP_VERSION_CHANGED,
								EventType.DOCUMENT_CHANGED,
								EventType.FORM_BUTTON_CLICKED]
	
    public void processEvents(RobotMessageBundle bundle) {
    Wavelet wavelet = bundle.getWavelet();
           
    if (bundle.wasSelfAdded()) {
     // Blip blip = wavelet.appendBlip();
Blip blip = e.getBlip();
     
processBible(blip)
    }
            
    for (Event e: bundle.getEvents()) {
   if (e.getType() == EventType.WAVELET_PARTICIPANTS_CHANGED) {    
      
Blip blip = e.getBlip();
              processBible(blip);

      }

      if (e.getType() == EventType.DOCUMENT_CHANGED) {    
Blip blip = e.getBlip();
processBible(blip);

//        TextView textView = blip.getDocument();
  //      textView.append(fetchWord());
      }
    
if (e.getType() == EventType.BLIP_SUBMITTED) {						
				Blip blip = e.getBlip();
				//Only process standard blips (i.e. not title blip)
			//if(blip.getBlipId().equals(blip.getWavelet().getRootBlipId()) == false){
					//Default action - Process text and insert the emoticons
					processBible(blip);
				}
				
		//}

    }
    }
private processBible(Blip blip){
TextView doc = blip.getDocument();
//(bible://kjv/gen/1/1)
            String verse=fetchWord();
		String blipContent = doc.getText();
     if(blipContent?.contains("#bible")){          
  blip.getDocument().replace(blipContent.replace("#bible",verse))
 	    }
}
private String fetchWord(){
    StringBuffer sb=new StringBuffer();
    BufferedReader reader=null;
    try {
      URL url = new URL("http://rock.ccim.org/gsword/gbook/randomVerseText");
      reader = new BufferedReader(new InputStreamReader(url.openStream()));
       String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line+"\n");
            }
           

    } catch (MalformedURLException e) {
      // ...
    } catch (IOException e) {
      // ...
    } finally {
      if(reader!=null){
      try{
      reader.close();
      }catch (Exception e){};
     }
    }
    return sb.toString();
  }
 
}
