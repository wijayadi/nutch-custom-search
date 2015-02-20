package ir.co.bayan.simorq.zal.extractor.model;

import ir.co.bayan.simorq.zal.extractor.evaluation.EvaluationContext;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.Validate;

/**
 * Evaluates the content.
 * 
 * @author Taha Ghasemi <taha.ghasemi@gmail.com>
 * 
 */
@XmlRootElement
public class Process extends Function {

	@XmlIDREF
	@XmlAttribute(name = "processor")
	private ProcessorDef processor;

	@SuppressWarnings("unchecked")
	@Override
	public List<?> extract(Object root, EvaluationContext context) throws Exception {
		Validate.isTrue(args != null && args.size() == 1, "Only one inner functions are expected.");

		List<Object> res = (List<Object>) args.get(0).extract(root, context);
		for (int i = 0; i < res.size(); i++) {
			res.set(i, processor.getProcessorInstance().process(res.get(i)));
		}
		return res;
	}

	@Override
	public String toString() {
		return "Process [" + super.toString() + "]";
	}

}