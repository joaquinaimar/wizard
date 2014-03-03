using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;

namespace Wizard.Wpf.Program.Basic.UI.Tab
{
    class Tab : TabItem
    {


        // Constructor
        public Tab()
        {
            TabHeader tabHeader = new TabHeader();

            // Assign the usercontrol to the tab header
            this.Header = tabHeader;

            tabHeader.button_close.MouseEnter += new MouseEventHandler(button_close_MouseEnter);
            tabHeader.button_close.MouseLeave += new MouseEventHandler(button_close_MouseLeave);
            tabHeader.button_close.Click += new RoutedEventHandler(button_close_Click);
            tabHeader.label_TabTitle.SizeChanged += new SizeChangedEventHandler(label_TabTitle_SizeChanged);
        }

        public string Title
        {
            get
            {
                return ((TabHeader)this.Header).label_TabTitle.Content as string;
            }
            set
            {
                ((TabHeader)this.Header).label_TabTitle.Content = value;
            }
        }

        protected override void OnSelected(RoutedEventArgs e)
        {
            base.OnSelected(e);
            ((TabHeader)this.Header).button_close.Visibility = Visibility.Visible;
        }

        protected override void OnUnselected(RoutedEventArgs e)
        {
            base.OnUnselected(e);
            ((TabHeader)this.Header).button_close.Visibility = Visibility.Hidden;
        }

        protected override void OnMouseEnter(MouseEventArgs e)
        {
            base.OnMouseEnter(e);
            ((TabHeader)this.Header).button_close.Visibility = Visibility.Visible;
        }

        protected override void OnMouseLeave(MouseEventArgs e)
        {
            base.OnMouseLeave(e);
            if (!this.IsSelected)
            {
                ((TabHeader)this.Header).button_close.Visibility = Visibility.Hidden;
            }
        }

        void button_close_MouseEnter(object sender, MouseEventArgs e)
        {
            ((TabHeader)this.Header).button_close.Foreground = Brushes.Red;
        }

        void button_close_MouseLeave(object sender, MouseEventArgs e)
        {
            ((TabHeader)this.Header).button_close.Foreground = Brushes.Black;
        }

        void button_close_Click(object sender, RoutedEventArgs e)
        {
            ((TabControl)this.Parent).Items.Remove(this);
        }

        void label_TabTitle_SizeChanged(object sender, SizeChangedEventArgs e)
        {
            ((TabHeader)this.Header).button_close.Margin = new Thickness(((TabHeader)this.Header).label_TabTitle.ActualWidth + 5, 3, 4, 0);
        }

    }

}
