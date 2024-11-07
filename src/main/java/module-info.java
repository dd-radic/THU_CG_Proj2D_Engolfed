module com.thu_cg_proj2d_engolfed {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.thu_cg_proj2d_engolfed to javafx.fxml;
	exports com.thu_cg_proj2d_engolfed;
}