module com.thu_cg_proj2d_engolfed {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;


	opens com.thu_cg_proj2d_engolfed to javafx.fxml;
	exports com.thu_cg_proj2d_engolfed;
	exports com.thu_cg_proj2d_engolfed.gameobjects;
	opens com.thu_cg_proj2d_engolfed.gameobjects to javafx.fxml;
}