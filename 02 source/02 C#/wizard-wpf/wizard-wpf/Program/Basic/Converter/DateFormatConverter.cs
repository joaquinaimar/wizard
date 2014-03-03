using System;
using System.Globalization;
using System.Windows.Data;

namespace Wizard.Wpf.Program.Basic.Converter
{
    public class DateFormatConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            if (DateTime.MinValue == (DateTime)value)
                return string.Empty;
            if (DateTime.MinValue.AddYears(1800) == (DateTime)value)
                return string.Empty;
            return ((DateTime)value).ToString(parameter.ToString());
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            return parameter;
        }
    }
}
