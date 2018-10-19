package com.csye6225.fall2018.courseservice3.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice3.Datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice3.Datamodel.Lecture;

public class LectureService {

	static HashMap<String, Lecture> lecturesMap = InMemoryDatabase.getLecturesDB();
	
	//Adding a lecture with input as Lecture object and a courseId
			public Lecture addLecture(Lecture lecture) {
				String courseId = lecture.getCourseId();
				lecturesMap.put(courseId, lecture);
				return lecturesMap.get(courseId);
			}
		
		// Getting a list of all courses
		public List<Lecture> getAllLectures() {	
			ArrayList<Lecture> lectureList = new ArrayList<>();
			for (Lecture lecture : lecturesMap.values()) {
				lectureList.add(lecture);
			}
			return lectureList ;
		}
		
		// Getting a Lecture by courseId
		public Lecture getLecture(String courseId) {
			return lecturesMap.get(courseId);		
		}
		
		// Deleting a lecture
		public Lecture deleteLecture(String courseId) {		
			Lecture lectureToRemove = lecturesMap.get(courseId);
			lecturesMap.remove(courseId);
			return lectureToRemove;		
		}
		
		// Updating Lecture Info
		public Lecture updateLectureInfo(String courseId, List<String> updatedNotes) {
			Lecture lecture = lecturesMap.get(courseId);
			lecture.setNotes(updatedNotes);
			lecturesMap.put(courseId, lecture);
			return lecture;
		}
		//	
}
