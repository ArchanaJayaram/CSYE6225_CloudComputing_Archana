package com.csye6225.fall2018.courseservice3.Resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice3.Datamodel.Announcements;
import com.csye6225.fall2018.courseservice3.Service.AnnouncementsService;

@Path("announcements")
public class AnnouncementsResource {
	
	AnnouncementsService announcementsService = new AnnouncementsService();
	
		//Add a new announcement
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Announcements addAnnouncement(Announcements announcemen) {
				return announcementsService.addAnnouncement(announcemen);
		}	
		
		//Get all announcements
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Announcements> getAnnouncements() {
			return announcementsService.getAllAnnouncements();			
		}
		
		// ... webapi/announcements/1 
		//Get announcements by announcementId
		@GET
		@Path("/{boardId_announcementId}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Announcements> getAnnouncement(@PathParam("boardId_announcementId") String boardId_announcementId) {
			return announcementsService.getAnnouncement(boardId_announcementId);
		}
		
		//Delete a announcement by announcementId
		@DELETE
		@Path("/{boardId_announcementId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Announcements deleteAnnouncement(@PathParam("boardId_announcementId") String boardId_announcementId) {
			return announcementsService.deleteAnnouncement(boardId_announcementId);
		}
			
		//Update information for an existing announcement
		@PUT
		@Path("/{boardId_announcementId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Announcements updateAnnouncement(@PathParam("boardId_announcementId") String boardId_announcementId, Announcements announcement) {
			return announcementsService.updateAnnouncement(boardId_announcementId, announcement);
		}

}