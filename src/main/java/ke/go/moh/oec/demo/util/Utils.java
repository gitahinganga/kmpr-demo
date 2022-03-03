package ke.go.moh.oec.demo.util;

import ke.go.moh.oec.Person;
import ke.go.moh.oec.lib.Mediator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public final class Utils {

	public static Date parseDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (dateString != null) {
			dateString = dateString.trim();
			try {
				return sdf.parse(dateString);
			} catch (ParseException ex) {
				Mediator.getLogger(Utils.class.getName()).log(Level.WARNING, "Un-parsable data: {0}.", new Object[]{dateString});
			}
		}
		return null;
	}

	public static String formatDate(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return null;
	}

	public static Person.Sex parseSex(String sexString) {
		if ("Male".equals(sexString)) {
			return Person.Sex.M;
		} else if ("Female".equals(sexString)) {
			return Person.Sex.F;
		}
		return null;
	}
}
