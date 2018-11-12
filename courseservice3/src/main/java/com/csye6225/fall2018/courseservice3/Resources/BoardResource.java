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

import com.csye6225.fall2018.courseservice3.Datamodel.Board;
import com.csye6225.fall2018.courseservice3.Service.BoardService;

@Path("board")
public class BoardResource {
	
	BoardService boardService = new BoardService();
	
		//Add a new board
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Board addBoard(Board board) {
				return boardService.addBoard(board);
		}	
		
		//Get all boards
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Board> getBoard() {
			return boardService.getAllboards();			
		}
		
		// ... webapi/courses/1 
		//Get boards by boardId
		@GET
		@Path("/{boardId}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Board> getBoard(@PathParam("boardId") String boardId) {
			return boardService.getBoard(boardId);
		}
		
		//Delete a board by boardId
		@DELETE
		@Path("/{boardId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Board deleteBoard(@PathParam("boardId") String boardId) {
			return boardService.deleteBoard(boardId);
		}
			
		//Update information for an existing board
		@PUT
		@Path("/{courseId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Board updateBoard(@PathParam("boardId") String boardId, Board Board) {
			return boardService.updateBoard(boardId, Board);
		}

}
